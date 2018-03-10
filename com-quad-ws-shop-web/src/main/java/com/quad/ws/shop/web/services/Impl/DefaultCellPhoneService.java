package com.quad.ws.shop.web.services.Impl;

import com.quad.ws.shop.web.daos.CellPhoneDao;
import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.CellPhone;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.CellPhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.quad.ws.shop.web.error.message.ExceptionMessage.EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID;

@Service
public class DefaultCellPhoneService implements CellPhoneService {
    @Autowired
    CellPhoneDao cellPhoneDao;
    @Autowired
    ProductDao productDao;
    private static final Logger log = LoggerFactory.getLogger(DefaultProductService.class);

    @Override
    public void addCellPhone(CellPhone cellPhone) throws DuplicateProductIdException {

        Long duplicateProductId=cellPhone.getProductId();

        Product duplicateProduct=productDao.findOne(duplicateProductId);
        log.info("ProductID "+duplicateProductId);

        log.info("CellPhone  "+duplicateProduct);
        if(null != duplicateProduct){
            throw new DuplicateProductIdException(duplicateProductId,EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID);
        }

        cellPhoneDao.save(cellPhone);
    }
}
