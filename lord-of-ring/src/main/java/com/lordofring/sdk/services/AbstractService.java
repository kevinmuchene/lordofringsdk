package com.lordofring.sdk.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.filters.QueryFilter;
import com.lordofring.sdk.filters.QueryFilterType;
import com.lordofring.sdk.api.http.HttpRequestExecutorInterface;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> {

    Logger logger = LoggerFactory.getLogger(getClass());

    protected ObjectMapper objectMapper;

    private final HttpRequestExecutorInterface httpRequestExecutorInterface;

    public AbstractService(HttpRequestExecutorInterface httpRequestExecutorInterface, ObjectMapper objectMapper) {
        this.httpRequestExecutorInterface = httpRequestExecutorInterface;
        this.objectMapper = objectMapper;
    }

    protected ApiResponse<List<T>> fetchList(String url, TypeReference<List<T>> typeReference) {
        return fetchDataAsList(url, typeReference);
    }


    protected ApiResponse<List<T>> fetchList(String url, TypeReference<List<T>> typeReference, List<QueryFilter> queryFilters) {

        String filteredUrl = applyFilters(url, queryFilters);
        return fetchDataAsList(filteredUrl, typeReference);
    }


    protected ApiResponse<T> fetchSingle(String url, TypeReference<T> typeReference) {

        return fetchDataAsSingle(url, typeReference);
    }

    protected List<T> parseList(String json, TypeReference<List<T>> typeReference) {

        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode docsNode = rootNode.get("docs");
            return objectMapper.convertValue(docsNode, typeReference);
        } catch (IOException e) {
            logger.error("Failed to parse list: {}", e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    protected T parseSingle(String json, TypeReference<T> typeReference) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode docsNode = rootNode.get("docs");
            if (docsNode.isArray() && !docsNode.isEmpty()) {
                JsonNode firstNode = docsNode.get(0);
                return objectMapper.convertValue(firstNode, typeReference);
            } else {
                throw new IllegalArgumentException("No data found in 'docs' array.");
            }
        } catch (IOException e) {
            logger.error("Failed to parse single object: {}", e.getMessage(), e);
        }
        return null;
    }


    private ApiResponse<List<T>> fetchDataAsList(String url, TypeReference<List<T>> typeReference) {
        HttpClientResponseHandler<ApiResponse<List<T>>> responseHandler = response -> {
            int status = response.getCode();
            HttpEntity entity = response.getEntity();

            if (status >= 200 && status < 300) {
                if (entity != null) {
                    String jsonResponse = EntityUtils.toString(entity);
                    List<T> dataList = parseList(jsonResponse, typeReference);
                    return new ApiResponse<>(true, dataList);
                }
            } else if (status == 401) {
                return new ApiResponse<>(false, "Unauthorized.");
            } else {
                return new ApiResponse<>(false, "Unexpected error. Status code: " + status);
            }

            return new ApiResponse<>(false, "Unknown error occurred.");
        };

        try {
            return httpRequestExecutorInterface.fetch(url, responseHandler);
        } catch (IOException e) {
            logger.error("Failed to fetch list: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to fetch data: " + e.getMessage());
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }



    private ApiResponse<T> fetchDataAsSingle(String url, TypeReference<T> typeReference) {
        HttpClientResponseHandler<ApiResponse<T>> responseHandler = response -> {
            int status = response.getCode();
            HttpEntity entity = response.getEntity();

            if (status >= 200 && status < 300) {
                if (entity != null) {
                    String jsonResponse = EntityUtils.toString(entity);
                    T data = parseSingle(jsonResponse, typeReference);
                    return new ApiResponse<>(true, data);
                }
            } else if (status == 401) {
                return new ApiResponse<>(false, "Unauthorized.");
            } else {
                return new ApiResponse<>(false, "Unexpected error. Status code: " + status);
            }
            return new ApiResponse<>(false, "Unknown error occurred.");
        };

        try {
            return httpRequestExecutorInterface.fetch(url, responseHandler);
        } catch (IOException | HttpException e) {
            logger.error("Failed to data as single: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to fetch data: " + e.getMessage());
        }
    }


    private String applyFilters(String baseUrl, List<QueryFilter> queryFilters) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        if (queryFilters != null && !queryFilters.isEmpty()) {
            urlBuilder.append("?");
            for (QueryFilter queryFilter : queryFilters) {
                String filterString = buildFilterString(queryFilter);
                urlBuilder.append(filterString).append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1);
        }
        return urlBuilder.toString();
    }


    private String buildFilterString(QueryFilter queryFilter) {
        String field = queryFilter.getField();
        QueryFilterType type = queryFilter.getFilterType();
        String value = URLEncoder.encode(queryFilter.getValue(), StandardCharsets.UTF_8);
        switch (type) {
            case EQUALS:
            case INCLUDE:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + "=" + value;
            case NOT_EQUALS:
            case EXCLUDE:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + "!=" + value;
            case EXISTS:
                return URLEncoder.encode(field, StandardCharsets.UTF_8);
            case NOT_EXISTS:
                return "!" + URLEncoder.encode(field, StandardCharsets.UTF_8);
            case REGEX:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + "=/" + value + "/";
            case LESS_THAN:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + URLEncoder.encode("<", StandardCharsets.UTF_8) + value;
            case GREATER_THAN:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + URLEncoder.encode(">", StandardCharsets.UTF_8) + value;
            case LESS_THAN_EQUAL:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + URLEncoder.encode("<=", StandardCharsets.UTF_8) + value;
            case GREATER_THAN_EQUAL:
                return URLEncoder.encode(field, StandardCharsets.UTF_8) + URLEncoder.encode(">=", StandardCharsets.UTF_8) + value;
            default:
                throw new IllegalArgumentException("Invalid filter type");
        }
    }
}
