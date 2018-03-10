package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.Clothing;

public interface ClothingService {
    void addClothing(Clothing clothing) throws DuplicateProductIdException;
}
