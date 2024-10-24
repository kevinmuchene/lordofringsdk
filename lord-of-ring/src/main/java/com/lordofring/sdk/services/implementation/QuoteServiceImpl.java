package com.lordofring.sdk.services.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordofring.sdk.api.ApiConfig;
import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.api.http.HttpRequestExecutorInterface;
import com.lordofring.sdk.filters.QueryFilter;
import com.lordofring.sdk.models.Quote;
import com.lordofring.sdk.services.AbstractService;
import com.lordofring.sdk.services.QuoteService;
import com.lordofring.sdk.api.http.HttpFetcherRequestExecutor;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import java.util.List;

public class QuoteServiceImpl extends AbstractService<Quote> implements QuoteService {

    public QuoteServiceImpl(String bearer) {
        super(new HttpFetcherRequestExecutor(HttpClients.createDefault(), bearer), new ObjectMapper());
    }

    public QuoteServiceImpl(HttpRequestExecutorInterface httpRequestExecutorInterface, ObjectMapper objectMapper) {
        super(httpRequestExecutorInterface, objectMapper);
    }

    @Override
    public ApiResponse<List<Quote>> getQuotes() {
        return fetchList(ApiConfig.BASE_URL + "/quote", new TypeReference<>() {});
    }

    @Override
    public ApiResponse<Quote> getQuoteById(String id) {
        return fetchSingle(ApiConfig.BASE_URL + "/quote/" + id, new TypeReference<>() {});
    }

    @Override
    public ApiResponse<List<Quote>> getQuotesByMovieId(String movieId) {
        return fetchList(ApiConfig.BASE_URL + "/movie/" + movieId + "/quote", new TypeReference<>() {});
    }

    @Override
    public ApiResponse<List<Quote>> getFilteredQuotes(List<QueryFilter> queryFilters) {
        String baseUrl = ApiConfig.BASE_URL + "/quote";
        return fetchList(baseUrl, new TypeReference<>() {
        }, queryFilters);
    }
}
