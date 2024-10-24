package com.lordofring.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.models.Movie;
import com.lordofring.sdk.services.implementation.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceImplTest {

    private MovieServiceImpl movieServiceImpl;

    @BeforeEach
    void setUp() {

        Map<String, String> mockResponses = new HashMap<>();

        mockResponses.put("/movie/5cd95395de30eff6ebccde58",
                "{\"docs\":[{\"_id\":\"5cd95395de30eff6ebccde58\", \"name\":\"The Unexpected Journey\", \"runtimeInMinutes\":169, \"budgetInMillions\":200, \"boxOfficeRevenueInMillions\":1021, \"academyAwardNominations\":3, \"academyAwardWins\":1, \"rottenTomatoesScore\":64}], \"total\":1, \"limit\":1000, \"offset\":0, \"page\":1, \"pages\":1}");

        InMemoryHttpRequestExecutorTest inMemoryExecutor = new InMemoryHttpRequestExecutorTest(mockResponses);
        movieServiceImpl = new MovieServiceImpl(inMemoryExecutor, new ObjectMapper());
    }

    @Test
    void testGetMovieById_Success() {

        ApiResponse<Movie> response = movieServiceImpl.getMovieById("5cd95395de30eff6ebccde58");

        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals("5cd95395de30eff6ebccde58", response.getData().getId());
        assertEquals("The Unexpected Journey", response.getData().getName());
    }

    @Test
    void testGetMovieById_Unauthorized() {

        ApiResponse<Movie> response = movieServiceImpl.getMovieById("unknown");

        assertFalse(response.isSuccess());
        assertEquals("Unauthorized.", response.getMessage());
    }

    @Test
    void testGetMovieById_Failure() {

        ApiResponse<Movie> response = movieServiceImpl.getMovieById("2");

        assertFalse(response.isSuccess());
        assertEquals("Unexpected error. Status code: 500", response.getMessage());
    }


}
