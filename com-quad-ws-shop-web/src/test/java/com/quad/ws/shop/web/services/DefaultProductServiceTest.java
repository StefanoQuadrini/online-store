package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.CategoryNotFounException;
import com.quad.ws.shop.web.error.exception.ResourceNotFoundException;
import com.quad.ws.shop.web.pojo.HandMade;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.Impl.DefaultProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(SpringRunner.class)
public class DefaultProductServiceTest {
    @InjectMocks
    private DefaultProductService defaultProductService;

    @Mock
    private ProductDao productDao;

    private Product dummyProduct;
    @Test
    public void getProductById_whenProductExists_thenReturnIsSuccessful() {
        // given
        givenDummyìProduct();
        given(productDao.findOne(anyLong())).willReturn(dummyProduct);

        // when
        Product result = defaultProductService.getProductById(anyLong());

        // then
        assertNotNull(result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getProductById_whenProductNotExist_thenResourceNotFoundException() {
        // given
        given(productDao.findOne(anyLong())).willReturn(null);

        // when
        defaultProductService.getProductById(anyLong());
    }

    @Test(expected = CategoryNotFounException.class)
    public void getProductByCategory_whenProductNotExist_thenResourceNotFoundException() {
        // given
        given(productDao.findOne(anyLong())).willReturn(null);

        // when
        defaultProductService.getProductsByCategory(anyString());
    }

    private void givenDummyìProduct() {
        dummyProduct = new HandMade(114L,"earrings",110.36,"All the component are worked with hands", Product.Categories.HANDMADE,"original handmade product","Germany");
    }





}
