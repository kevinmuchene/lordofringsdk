package com.lordofring.sdk;

import com.lordofring.sdk.api.ApiConfig;
import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.filters.QueryFilter;
import com.lordofring.sdk.filters.QueryFilterType;
import com.lordofring.sdk.models.Movie;
import com.lordofring.sdk.models.Quote;
import com.lordofring.sdk.services.implementation.MovieServiceImpl;
import com.lordofring.sdk.services.implementation.QuoteServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) {

        //SET BEARER TOKEN

       /* ApiConfig.setBearerToken("Your-Bearer-Token");

        MovieServiceImpl movieService = new MovieServiceImpl(ApiConfig.getBearerToken());
        QuoteServiceImpl quoteService = new QuoteServiceImpl(ApiConfig.getBearerToken());


        */

        //FETCH MOVIES

        /*
        System.out.println("FETCH MOVIES");
        ApiResponse<List<Movie>> movies = movieService.getMovies();

        if(movies.isSuccess()) {
            List<Movie> moviesList = movies.getData();
            for(Movie movie : moviesList) {
                System.out.println(movie.toString());
            }
        }

        */


        // FETCH QUOTES

        /*
        System.out.println("\n\n\n\n");
        System.out.println("FETCH QUOTES");
        ApiResponse<List<Quote>> quotes = quoteService.getQuotes();

        if(quotes.isSuccess()) {
            List<Quote> quotesList = quotes.getData();
            for(Quote quote : quotesList) {
                System.out.println(quote.toString());
            }
        }
        */


        //FETCH MOVIE BY ID

        /*
       System.out.println("\n\n\n\n");
        System.out.println("FETCH MOVIE BY ID");
        String movieId = "5cd95395de30eff6ebccde5c";

        ApiResponse<Movie> movie = movieService.getMovieById(movieId);

        if(movie.isSuccess()) {
            Movie movieInfo = movie.getData();
            System.out.println(movieInfo.toString());
        } else {
            System.out.println(movie.getMessage());
        }

        */


        //FETCH QUOTE BY ID

        /*
        System.out.println("\n\n\n\n");
        System.out.println("FETCH QUOTE BY ID");
        String quoteId = "5cd96e05de30eff6ebcce7e9";

        ApiResponse<Quote> quote = quoteService.getQuoteById(quoteId);

        if(quote.isSuccess()) {
            Quote quoteInfo = quote.getData();
            System.out.println(quoteInfo.toString());
        }
        */

       //FETCH QUOTES BY MOVIE ID

        /*
        System.out.println("\n\n\n\n");
        System.out.println("FETCH QUOTES BY MOVIE ID");
        String movieId2 = "5cd95395de30eff6ebccde5c";

        ApiResponse<List<Quote>> movieQuotes = quoteService.getQuotesByMovieId(movieId2);

        if(movieQuotes.isSuccess()) {
            List<Quote> quotesList = movieQuotes.getData();

            for (Quote quote2 : quotesList) {
                System.out.println(quote2.toString());
            }
        }
        */

        //TEST FILTERS

        /*
        System.out.println("\n\n\n\n");
        System.out.println("QUOTE FILTERS THAT MATCH A SPECIFIC CHARACTER, SPECIFIC MOVIE");



                List<QueryFilter> filters = new ArrayList<>();

                filters.add(new QueryFilter("character", QueryFilterType.EQUALS, "5cd99d4bde30eff6ebccfe9e"));

                filters.add(new QueryFilter("movie", QueryFilterType.EQUALS, "5cd95395de30eff6ebccde5d"));


        ApiResponse<List<Quote>> filteredQuotes = quoteService.getFilteredQuotes(filters);

        if(filteredQuotes.isSuccess()) {
            List<Quote> quotes2 = filteredQuotes.getData();

            for(Quote quote4 : quotes2) {
                System.out.println(quote4);
            }
        } else {
            System.out.println(filteredQuotes.getMessage());
        }

         */

    }
}
