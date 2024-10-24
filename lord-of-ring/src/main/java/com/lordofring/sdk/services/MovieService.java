package com.lordofring.sdk.services;

import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.filters.QueryFilter;
import com.lordofring.sdk.models.Movie;

import java.util.List;

public interface MovieService {

    ApiResponse<List<Movie>> getMovies();

    ApiResponse<Movie> getMovieById(String id);

    ApiResponse<List<Movie>> getFilteredMovies(List<QueryFilter> queryFilters);

}
