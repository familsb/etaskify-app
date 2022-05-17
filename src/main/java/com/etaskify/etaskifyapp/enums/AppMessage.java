package com.etaskify.etaskifyapp.enums;

public enum AppMessage {
    ORGANIZATION_CREATED(200, "Organization is created successful"),
    TASK_CREATED(201, "Task is created successful"),
    USER_CREATED(202, "User is created successful"),
    TASK_LIST_SUCCESS(203, "Task list successful"),
    ALREADY_HAVE(401, "Email already exists"),
    USER_NOT_FOUND(405, "User not found"),
    USERNAME_OR_PASSWORD_INCORRECT(404, "Username or password is incorrect"),
    FAILED_SEND_EMAIL(406, "Failed to send an email"),
    ORGANIZATION_NOT_FOUND(407, "Organization not found");

    private int code;
    private String message;

    AppMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
