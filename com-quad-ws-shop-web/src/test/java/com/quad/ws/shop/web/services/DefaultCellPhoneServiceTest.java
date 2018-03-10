package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.daos.CellPhoneDao;
import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.CellPhone;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.services.Impl.DefaultCellPhoneService;
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

public class DefaultCellPhoneServiceTest {

    @InjectMocks
    private DefaultCellPhoneService defaultCellPhoneService;

    @Mock
    private CellPhoneDao cellPhoneDao;

    @Mock
    private ProductDao productDao;

    private CellPhone dummyCellPhone;


    @Test(expected = DuplicateProductIdException.class)
    public void addCellPhone_whenCellPhoneIsDuplicate_thenDuplicateProductIdException() {
        // given
        givenDummyCellPhone();
        given(productDao.findOne(anyLong())).willReturn(new CellPhone());

        // when
        defaultCellPhoneService.addCellPhone(dummyCellPhone);
    }

    @Test
    public void addCellPhone_whenCellPhoneIsNotDuplicate_thenIsSaved() {
        // given
        givenDummyCellPhone();
        given(productDao.findOne(dummyCellPhone.getProductId())).willReturn(null);

        // when
        defaultCellPhoneService.addCellPhone(dummyCellPhone);

        // then
        verify(cellPhoneDao, times(1)).save(any(CellPhone.class));
    }

    private void givenDummyCellPhone() {
        dummyCellPhone = new CellPhone(112L,"Huawei",500.33,"Goog Quality respect price", Product.Categories.CELL_PHONES,"Android","Camera 5x","i7","F55Px",2);
    }
}
