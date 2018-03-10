package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.HandMade;

public interface HandMadeService {
    void addHandMade(HandMade handMade) throws DuplicateProductIdException;
}
