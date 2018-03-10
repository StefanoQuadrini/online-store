package com.quad.ws.shop.web.error.exception;

public class CategoryNotFounException extends RuntimeException {
    private String  category;

    public CategoryNotFounException(String  category, String message) {
        super(message);
        this.category = category;
    }
}
