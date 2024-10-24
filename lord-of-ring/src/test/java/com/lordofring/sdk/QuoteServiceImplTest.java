package com.lordofring.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordofring.sdk.api.ApiResponse;
import com.lordofring.sdk.models.Quote;
import com.lordofring.sdk.services.implementation.QuoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class QuoteServiceImplTest {

    private QuoteServiceImpl quoteServiceImpl;

    @BeforeEach
    void setup() {
        Map<String, String> mockResponses = new HashMap<>();
        mockResponses.put("/quote/5cd96e05de30eff6ebcce7e9",
                "{\"docs\":[{\"_id\":\"5cd96e05de30eff6ebcce7e9\", \"dialog\":\"Deagol!!\", \"character\":\"5cd99d4bde30eff6ebccfe9e\", \"movie\":\"5cd95395de30eff6ebccde5d\"}], \"total\":1, \"limit\":1000, \"offset\":0, \"page\":1, \"pages\":1}");

        InMemoryHttpRequestExecutorTest inMemoryExecutor = new InMemoryHttpRequestExecutorTest(mockResponses);
        quoteServiceImpl = new QuoteServiceImpl(inMemoryExecutor, new ObjectMapper());
    }

    @Test
    void testGetQuoteById_Success() {
        ApiResponse<Quote> response = quoteServiceImpl.getQuoteById("5cd96e05de30eff6ebcce7e9");

        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals("5cd96e05de30eff6ebcce7e9", response.getData().getId());
        assertEquals("Deagol!!", response.getData().getDialog());
    }

    @Test
    void testGetQuoteById_Unauthorized() {

        ApiResponse<Quote> response = quoteServiceImpl.getQuoteById("unknown");

        assertFalse(response.isSuccess());
        assertEquals("Unauthorized.", response.getMessage());
    }

    @Test
    void testGetMovieById_Failure() {

        ApiResponse<Quote> response = quoteServiceImpl.getQuoteById("874582");

        assertFalse(response.isSuccess());
        assertEquals("Unexpected error. Status code: 500", response.getMessage());
    }
}
