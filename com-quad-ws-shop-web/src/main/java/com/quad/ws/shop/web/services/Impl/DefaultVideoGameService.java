package com.quad.ws.shop.web.services.Impl;

import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.daos.VideoGameDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.pojo.VideoGame;
import com.quad.ws.shop.web.services.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.quad.ws.shop.web.error.message.ExceptionMessage.EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID;

@Service
public class DefaultVideoGameService implements VideoGameService{
    @Autowired
    VideoGameDao videoGameDao;
    @Autowired
    ProductDao productDao;
    @Override
    public void addVideoGame(VideoGame videoGame)  throws DuplicateProductIdException {
        Long duplicateProductId= videoGame.getProductId();

        Product duplicateProduct=productDao.findOne(duplicateProductId);

        if(null != duplicateProduct){
            throw new DuplicateProductIdException(duplicateProductId,EXCEPTION_MESSAGE_DUPLICATE_PRODUCT_ID);
        }

        videoGameDao.save(videoGame);
    }

}
