package com.lordofring.sdk.services.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordofring.sdk.api.ApiConfig;
import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.filters.QueryFilter;
import com.lordofring.sdk.models.Movie;
import com.lordofring.sdk.services.AbstractService;
import com.lordofring.sdk.services.MovieService;
import com.lordofring.sdk.api.http.HttpRequestExecutorInterface;
import com.lordofring.sdk.api.http.HttpFetcherRequestExecutor;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.util.List;

public class MovieServiceImpl extends AbstractService<Movie> implements MovieService {


    public MovieServiceImpl(String bearerToken) {
        super(new HttpFetcherRequestExecutor(HttpClients.createDefault(), bearerToken), new ObjectMapper());
    }

    public MovieServiceImpl(HttpRequestExecutorInterface httpRequestExecutorInterface, ObjectMapper objectMapper) {
        super(httpRequestExecutorInterface, objectMapper);
    }


    @Override
    public ApiResponse<List<Movie>> getMovies() {

        return fetchList(ApiConfig.BASE_URL + "/movie", new TypeReference<>() {
        });
    }

    @Override
    public ApiResponse<Movie> getMovieById(String id) {
        return fetchSingle(ApiConfig.BASE_URL + "/movie/" + id, new TypeReference<Movie>() {});
    }

    @Override
    public ApiResponse<List<Movie>> getFilteredMovies(List<QueryFilter> queryFilters) {
        String baseUrl = ApiConfig.BASE_URL + "/movie";
        return fetchList(baseUrl, new TypeReference<>() {
        }, queryFilters);
    }

}
