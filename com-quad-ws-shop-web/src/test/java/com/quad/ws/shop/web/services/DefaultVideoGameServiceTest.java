package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.daos.ProductDao;
import com.quad.ws.shop.web.daos.VideoGameDao;
import com.quad.ws.shop.web.error.exception.DuplicateProductIdException;
import com.quad.ws.shop.web.pojo.Product;
import com.quad.ws.shop.web.pojo.VideoGame;
import com.quad.ws.shop.web.services.Impl.DefaultVideoGameService;
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

public class DefaultVideoGameServiceTest {

    @InjectMocks
    private DefaultVideoGameService defaultVideoGameService;

    @Mock
    private VideoGameDao videoGameDao;

    @Mock
    private ProductDao productDao;

    private VideoGame dummyVideoGame;

    @Test(expected = DuplicateProductIdException.class)
    public void addHandMade_whenHandMadeIsDuplicate_thenDuplicateProductIdException() {
        // given
        givenDummyìVideogame();
        given(productDao.findOne(anyLong())).willReturn(new VideoGame());

        // when
        defaultVideoGameService.addVideoGame(dummyVideoGame);
    }

    @Test
    public void addHandMade_whenHandMadeIsNotDuplicate_thenIsSaved() {
        // given
        givenDummyìVideogame();
        given(productDao.findOne(dummyVideoGame.getProductId())).willReturn(null);

        // when
        defaultVideoGameService.addVideoGame(dummyVideoGame);

        // then
        verify(videoGameDao, times(1)).save(any(VideoGame.class));
    }






    private void givenDummyìVideogame() {
        dummyVideoGame = new VideoGame(1L,"Call of Duty",50.9,"Amazing War game", Product.Categories.VIDEO_GAMES,"Playstation 3", "ff");
    }
}
