package com.quad.ws.shop.web.services.Impl;

import com.quad.ws.shop.web.daos.HandMadeDao;
import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.HandMade;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.HandMadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.quad.ws.shop.web.error.message.ExceptionMessage.EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID;

@Service
public class DefaultHandMadeService implements HandMadeService {
    @Autowired
    HandMadeDao handMadeDao;
    @Autowired
    ProductDao productDao;
    @Override
    public void addHandMade(HandMade handMade) throws DuplicateProductIdException {
        Long duplicateProductId=handMade.getProductId();

        Product duplicateProduct=productDao.findOne(duplicateProductId);

        if(null != duplicateProduct){
            throw new DuplicateProductIdException(duplicateProductId,EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID);
        }

        handMadeDao.save(handMade);
    }

}
