package com.etaskify.etaskifyapp.enums;

public enum AppMessage {
    ORGANIZATION_CREATED(200, "Organization is created successful"),
    TASK_CREATED(201, "Task is created successful"),
    USER_CREATED(202, "User is created successful"),
    ALREADY_HAVE(401, "Email already exists"),
    USER_LIST_EMPTY(403, "User List is empty"),
    USER_NOTE_FOUND(405, "User not found"),
    USERNAME_OR_PASSWORD_INCORRECT(404, "Username or password is incorrect");

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
