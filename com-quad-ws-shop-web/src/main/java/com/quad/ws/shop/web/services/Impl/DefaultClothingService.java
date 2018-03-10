package com.quad.ws.shop.web.services.Impl;


import com.quad.ws.shop.web.daos.ClothingDao;
import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.Clothing;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.ClothingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.quad.ws.shop.web.error.message.ExceptionMessage.EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID;

@Service
public class DefaultClothingService implements ClothingService {
    @Autowired
    ClothingDao clothingDao;
    @Autowired
    ProductDao productDao;
    private static final Logger log= LoggerFactory.getLogger(DefaultClothingService.class);

    @Override
    public void addClothing(Clothing clothing) throws DuplicateProductIdException {
        Long duplicateProductId=clothing.getProductId();
        Product duplicateProduct=productDao.findOne(duplicateProductId);

        if(null != duplicateProduct){

            throw new DuplicateProductIdException(duplicateProductId, EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID);
        }
        clothingDao.save(clothing);
    }
}
