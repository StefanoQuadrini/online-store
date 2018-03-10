package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.daos.HandMadeDao;
import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.HandMade;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.Impl.DefaultHandMadeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DefaultHandMadeServiceTest {

    @InjectMocks
    private DefaultHandMadeService defaultHandMadeService;

    @Mock
    private HandMadeDao handMadeDao;

    @Mock
    private ProductDao productDao;


    HandMade dummyHandMade;

    @Test(expected = DuplicateProductIdException.class)
    public void addHandMade_whenHandMadeIsDuplicate_thenDuplicateProductIdException() {
        // given
        givenDummyHandMade();
        given(productDao.findOne(anyLong())).willReturn(new HandMade());

        // when
        defaultHandMadeService.addHandMade(dummyHandMade);
    }

    @Test
    public void addHandMade_whenHandMadeIsNotDuplicate_thenIsSaved() {
        // given
        givenDummyHandMade();

        given(productDao.findOne(dummyHandMade.getProductId())).willReturn(null);

        // when
        defaultHandMadeService.addHandMade(dummyHandMade);

        // then
        verify(handMadeDao, times(1)).save(any(HandMade.class));
    }


    private void givenDummyHandMade() {
        dummyHandMade = new HandMade(114L,"earrings",110.36,"All the component are worked with hands", Product.Categories.HANDMADE,"original handmade product","Germany");
    }
}
