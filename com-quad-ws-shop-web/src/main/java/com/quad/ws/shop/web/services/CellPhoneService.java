package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.CellPhone;

public interface CellPhoneService {
    void addCellPhone(CellPhone cellPhone) throws DuplicateProductIdException;
}

