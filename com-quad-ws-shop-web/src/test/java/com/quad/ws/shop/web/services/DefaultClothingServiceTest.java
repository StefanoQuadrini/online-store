package com.quad.ws.shop.web.services;


import com.quad.ws.shop.web.ShopOnlineApplication;
import com.quad.ws.shop.web.daos.ClothingDao;
import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.Clothing;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.Impl.DefaultClothingService;
import com.quad.ws.shop.web.services.Impl.DefaultProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)

public class DefaultClothingServiceTest {

    @InjectMocks
    private DefaultClothingService defaultClothingService;

    @InjectMocks
    private DefaultProductService defaultProductService;

    @Mock
    private ClothingDao clothingDao;

    @Mock
    private ProductDao productDao;

    Clothing dummyClothing;

    private static final Logger log = LoggerFactory.getLogger(DefaultClothingServiceTest.class);



    @Test(expected = DuplicateProductIdException.class)
    public void addClothing_whenClothingIsDuplicate_thenDuplicateProductIdException() {
        // given
        givenDummyClothing();
        given(productDao.findOne(anyLong())).willReturn(new Clothing());

        // when
        defaultClothingService.addClothing(dummyClothing);
    }

    @Test
    public void addClothing_whenClothingIsNotDuplicate_thenIsSaved() {
        // given
        givenDummyClothing();
        given(productDao.findOne(dummyClothing.getProductId())).willReturn(null);

        // when
        defaultClothingService.addClothing(dummyClothing);

        // then
        verify(clothingDao, times(1)).save(any(Clothing.class));
    }






    private void givenDummyClothing() {
        dummyClothing = new Clothing(1121L,"Socks",30,"very elegant", Product.Categories.CLOTHING,"L","Valentino","black");
    }


}


