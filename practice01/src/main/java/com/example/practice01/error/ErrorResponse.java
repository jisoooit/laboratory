package com.example.practice01.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    String statusCode;
    String requestUrl;
    String code;
    String message;
    String resultCode;

    List<Error> errorList;

    @Builder
    public ErrorResponse(String statusCode, String requestUrl, String code, String message, String resultCode, List<Error> errorList) {
        this.statusCode = statusCode;
        this.requestUrl = requestUrl;
        this.code = code;
        this.message = message;
        this.resultCode = resultCode;
        this.errorList = errorList;
    }
}
