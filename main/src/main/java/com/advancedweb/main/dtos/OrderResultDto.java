package com.advancedweb.main.dtos;

public class OrderResultDto {

    private boolean success;
    private String message;

    public OrderResultDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public OrderResultDto(){}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
