package com.quad.ws.shop.web.error.handler;


import com.quad.ws.shop.web.error.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractGlobalExceptionHandler {

    public ResponseEntity<ExceptionResponse> buildAndSendErrorResponse(Exception ex, String errorCode, HttpStatus httpStatus) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(errorCode);
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}
