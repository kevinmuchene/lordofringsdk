package com.lordofring.sdk.services;

import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.filters.QueryFilter;
import com.lordofring.sdk.models.Quote;

import java.util.List;

public interface QuoteService {

    ApiResponse<List<Quote>> getQuotes();

    ApiResponse<Quote>  getQuoteById(String id);

    ApiResponse<List<Quote>> getQuotesByMovieId(String movieId);

    ApiResponse<List<Quote>> getFilteredQuotes(List<QueryFilter> queryFilters);
}

