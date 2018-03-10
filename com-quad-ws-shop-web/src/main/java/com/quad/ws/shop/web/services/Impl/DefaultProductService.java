package com.quad.ws.shop.web.services.Impl;

import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.CategoryNotFounException;
import com.quad.ws.shop.web.error.exception.ResourceNotFoundException;
import com.quad.ws.shop.web.pojo.Clothing;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.quad.ws.shop.web.error.message.ExceptionMessage.EXCEPTION_MESSAGE_CATEGORY_NOT_FOUND;
import static com.quad.ws.shop.web.error.message.ExceptionMessage.EXCEPTION_MESSAGE_PRODUCT_NOT_FOUND;

@Service
public class DefaultProductService implements ProductService{

    @Autowired
    private ProductDao productDao;


    private static final Logger log = LoggerFactory.getLogger(DefaultProductService.class);

    @Override
    public Product getProductById(Long productId) throws ResourceNotFoundException {

        Product product=productDao.findOne(productId);

        if(null == product){

            throw new ResourceNotFoundException(productId, EXCEPTION_MESSAGE_PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(String category)  throws CategoryNotFounException {

        List<Product> list= productDao.findByCategories(category);

        if(null == list || list.isEmpty()){
            throw new CategoryNotFounException(category, EXCEPTION_MESSAGE_CATEGORY_NOT_FOUND);
        }

        return list;
    }
}
