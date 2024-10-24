package com.lordofring.sdk.api.http;

import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;

public interface HttpRequestExecutorInterface {
    <T> T fetch(String url, HttpClientResponseHandler<T> responseHandler) throws IOException, HttpException;
}
