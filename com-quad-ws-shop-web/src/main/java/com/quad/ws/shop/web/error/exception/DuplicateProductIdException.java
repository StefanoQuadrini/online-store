package com.quad.ws.shop.web.error.exception;

public class DuplicateProductIdException extends RuntimeException{
    private Long productId;

    public DuplicateProductIdException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }
}
