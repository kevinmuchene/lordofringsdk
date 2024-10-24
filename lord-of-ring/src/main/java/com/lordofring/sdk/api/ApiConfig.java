package com.lordofring.sdk.api;

public final class ApiConfig {

    public static final String BASE_URL = "https://the-one-api.dev/v2";

    private static String bearerToken;

    private ApiConfig() {}

    public static void setBearerToken(String token) {
        bearerToken = token;
    }

    public static String getBearerToken() {
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new IllegalStateException("Bearer token has not been set.");
        }
        return bearerToken;
    }
}

