package com.lordofring.sdk;

import com.lordofring.sdk.api.http.HttpRequestExecutorInterface;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InMemoryHttpRequestExecutorTest implements HttpRequestExecutorInterface {

    private final Map<String, String> mockResponses;

    public InMemoryHttpRequestExecutorTest(Map<String, String> mockResponses) {
        this.mockResponses = mockResponses;
    }

    @Override
    public <T> T fetch(String url, HttpClientResponseHandler<T> responseHandler) throws IOException {

        for (String key : mockResponses.keySet()) {
            if (url.contains(key)) {
                String jsonResponse = mockResponses.get(key);

                HttpEntity entity = new StringEntity(jsonResponse);
                CloseableHttpResponse httpResponse = mock(CloseableHttpResponse.class);
                when(httpResponse.getCode()).thenReturn(200);
                when(httpResponse.getEntity()).thenReturn(entity);
                try {
                    return responseHandler.handleResponse(httpResponse);
                } catch (HttpException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (url.contains("/unknown")) {
            try {
                return responseHandler.handleResponse(mockUnauthorizedResponse());
            } catch (HttpException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return responseHandler.handleResponse(mockErrorResponse());
            } catch (HttpException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private CloseableHttpResponse mockUnauthorizedResponse() {
        CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        when(response.getCode()).thenReturn(401);
        return response;
    }

    private CloseableHttpResponse mockErrorResponse() {
        CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        when(response.getCode()).thenReturn(500);
        HttpEntity entity = new StringEntity("{\"error\":\"Unexpected error\"}");
        when(response.getEntity()).thenReturn(entity);
        return response;
    }
}

