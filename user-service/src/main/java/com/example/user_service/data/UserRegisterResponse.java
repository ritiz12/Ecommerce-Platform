package com.example.user_service.data;

public class UserRegisterResponse {
    private String message;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    public UserRegisterResponse(final String message, final String userId, final String email, final String firstName, final String lastName) {
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(final String message) {
        this.message = message;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
