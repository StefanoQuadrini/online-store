package com.quad.ws.shop.web.dao;

import com.quad.ws.shop.web.daos.*;
import com.quad.ws.shop.web.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ClothingDao clothingDao;

    @Autowired
    private HandMadeDao handMadeDao;

    @Autowired
    private CellPhoneDao cellPhoneDao;

    @Autowired
    private VideoGameDao videoGameDao;

    @Autowired
    private ProductDao productDao;

    private Clothing dummyClothing;

    private CellPhone dummyCellPhone;

    private VideoGame dummyVideoGame;

    private HandMade dummyHandMade;

    @Test
    public void save_whenClothingIsSaved_thenCanBeRetrieved() {
        // given
        givenDummyClothing();
        testEntityManager.persist(dummyClothing);
        testEntityManager.flush();

        // when
        Clothing found = clothingDao.findOne(dummyClothing.getProductId());

        // then
        assertEquals(dummyClothing.getProductId(), found.getProductId());
    }

    @Test
    public void save_whenCellPhoneIsSaved_thenCanBeRetrieved() {
        // given
        givenDummyCellPhone();
        testEntityManager.persist(dummyCellPhone);
        testEntityManager.flush();

        // when
        CellPhone found = cellPhoneDao.findOne(dummyCellPhone.getProductId());

        // then
        assertEquals(dummyCellPhone.getProductId(), found.getProductId());
    }

    @Test
    public void save_whenVideoGameIsSaved_thenCanBeRetrieved() {
        // given
        givenDummyVideogame();
        testEntityManager.persist(dummyVideoGame);
        testEntityManager.flush();

        // when
        VideoGame found = videoGameDao.findOne(dummyVideoGame.getProductId());

        // then
        assertEquals(dummyVideoGame.getProductId(), found.getProductId());
    }

    @Test
    public void save_whenHandMadeIsSaved_thenCanBeRetrieved() {
        // given
        givenDummyHandMade();
        testEntityManager.persist(dummyHandMade);
        testEntityManager.flush();

        // when
        HandMade found = handMadeDao.findOne(dummyHandMade.getProductId());

        // then
        assertEquals(dummyHandMade.getProductId(), found.getProductId());
    }

    @Test
    public void save_whenHandMadeIsSaved_thenExistAProductWithSameId(){

        //given
        givenDummyHandMade();
        testEntityManager.persist(dummyHandMade);
        testEntityManager.flush();

        //when
        Product found = productDao.findOne(dummyHandMade.getProductId());

        //then
        assertEquals(dummyHandMade.getProductId(), found.getProductId());


    }

    @Test
    public void save_whenClothingIsSaved_thenExistAProductWithSameId(){

        //given
        givenDummyClothing();
        testEntityManager.persist(dummyClothing);
        testEntityManager.flush();

        //when
        Product found = productDao.findOne(dummyClothing.getProductId());

        //then
        assertEquals(dummyClothing.getProductId(), found.getProductId());


    }

    @Test
    public void save_whenVideoGameIsSaved_thenExistAProductWithSameId(){

        //given
        givenDummyVideogame();
        testEntityManager.persist(dummyVideoGame);
        testEntityManager.flush();

        //when
        Product found = productDao.findOne(dummyVideoGame.getProductId());

        //then
        assertEquals(dummyVideoGame.getProductId(), found.getProductId());


    }

    public void save_whenCellPhoneIsSaved_thenExistAProductWithSameId(){

        //given
        givenDummyCellPhone();
        testEntityManager.persist(dummyCellPhone);
        testEntityManager.flush();

        //when
        Product found = productDao.findOne(dummyCellPhone.getProductId());

        //then
        assertEquals(dummyCellPhone.getProductId(), found.getProductId());

    }
    private void givenDummyClothing() {
        dummyClothing = new Clothing(10L,"Socks",30,"very elegant", Product.Categories.CLOTHING,"L","Valentino","black");
    }

    private void givenDummyCellPhone() {
        dummyCellPhone = new CellPhone(10L,"Huawei",500.33,"Goog Quality respect price", Product.Categories.CELL_PHONES,"Android","Camera 5x","i7","F55Px",2);
    }

    private void givenDummyHandMade() {
        dummyHandMade = new HandMade(10L,"earrings",110.36,"All the component are worked with hands", Product.Categories.HANDMADE,"original handmade product","Germany");
    }

    private void givenDummyVideogame() {
        dummyVideoGame = new VideoGame(10L,"Call of Duty",50.9,"Amazing War game", Product.Categories.VIDEO_GAMES,"Playstation 3", "ff");
    }





}
