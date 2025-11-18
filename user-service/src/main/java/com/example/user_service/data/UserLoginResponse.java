package com.example.user_service.data;

public class UserLoginResponse {
    private String message;

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;
    public UserLoginResponse(final String loginSuccessful, final String accessToken, final String refreshToken, final Long expiresIn) {
    }

    public void setMessage(final String message) {
        this.message = message;
    }
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public void setExpiresIn(final Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
