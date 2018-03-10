package com.quad.ws.shop.web.error.handler;


import com.fasterxml.jackson.core.JsonParseException;
import com.quad.ws.shop.web.error.ExceptionResponse;
import com.quad.ws.shop.web.error.exception.CategoryNotFounException;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.error.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import static com.quad.ws.shop.web.error.message.ErrorCode.*;

@ControllerAdvice
public class GlobalExceptionHandler extends AbstractGlobalExceptionHandler {

    // HANDLING CUSTOM EXCEPTIONS
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFounException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFound(CategoryNotFounException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateProductIdException.class)
    public ResponseEntity<ExceptionResponse> productIdDuplicated(DuplicateProductIdException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_DUPLICATE_PRODUCT_ID, HttpStatus.CONFLICT);
    }


    // HANDLING SOME NON-CUSTOM EXCEPTIONS
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> constraintViolation(ConstraintViolationException ex) {
        return buildAndSendErrorResponse(ex, ERRROR_CODE_CONSTRAINT_VIOLATION, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ExceptionResponse> requestWithUnexpectedType(UnexpectedTypeException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> requestWithInvalidArgument(MethodArgumentNotValidException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> requestNotReadable(HttpMessageNotReadableException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ExceptionResponse> requestNotParsable(JsonParseException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ExceptionResponse> invalidRequest(TransactionSystemException ex) {
        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
    }
}
