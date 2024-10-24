package com.lordofring.sdk.api.http;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;

public class HttpFetcherRequestExecutor implements HttpRequestExecutorInterface {

    private final CloseableHttpClient httpClient;
    private final String bearerToken;

    public HttpFetcherRequestExecutor(CloseableHttpClient httpClient, String bearerToken) {
        this.httpClient = httpClient;
        this.bearerToken = bearerToken;
    }

    @Override
    public <T> T fetch(String url, HttpClientResponseHandler<T> responseHandler) throws IOException {

        HttpGet request = new HttpGet(url);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        return httpClient.execute(request, responseHandler);
    }
}
