package com.quad.ws.shop.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.quad.ws.shop.web.ShopOnlineApplication;
import com.quad.ws.shop.web.controllers.EntryController;
import com.quad.ws.shop.web.pojo.*;
import com.quad.ws.shop.web.services.Impl.*;
import com.quad.ws.shop.web.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* CONTROLLER INTEGRATION TESTING using MockMvc instance to setup a Spring MVC context with a web server */
@RunWith(MockitoJUnitRunner.Silent.class)

@WebMvcTest(controllers = EntryController.class, secure = false)
public class EntryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    private EntryController entryController;

    @Mock
    private DefaultProductService defaultProductService;

    @Mock
    private DefaultClothingService defaultClothingService;

    @Mock
    private DefaultVideoGameService defaultVideoGameService;

    @Mock
    private DefaultHandMadeService defaultHandMadeService;

    @Mock
    private DefaultCellPhoneService defaultCellPhoneService;
    private static final Logger log= LoggerFactory.getLogger(ShopOnlineApplication.class);

    private static final String BASE_URL = "/quad/ws";
    private static final String SHOP_SUBPATH = "shop";
    private List<String> listCategory = new ArrayList<String>();
    private Clothing dummyClothing;

    private CellPhone dummyCellPhone;

    private VideoGame dummyVideoGame;

    private HandMade dummyHandMade;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entryController)
                // .addFilters(new CORSFilter())
                .build();


    }
    @Test
    public void get_whenClothingExists_thenResponseIs200() throws Exception {
        //given
        givenDummyClothing();
        given(defaultProductService.getProductById(dummyClothing.getProductId())).willReturn(dummyClothing);
        //when-then

        mockMvc.perform(get(buildGetUrlWithIdVariable(SHOP_SUBPATH), dummyClothing.getProductId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void get_whenCellPhoneExists_thenResponseIs200() throws Exception {
        //given
        givenDummyCellPhone();
        given(defaultProductService.getProductById(dummyCellPhone.getProductId())).willReturn(dummyCellPhone);
        //when-then
        mockMvc.perform(get(buildGetUrlWithIdVariable(SHOP_SUBPATH), dummyCellPhone.getProductId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void get_whenVideGameExists_thenResponseIs200() throws Exception {
        //given
        givenDummyVideogame();
        given(defaultProductService.getProductById(dummyVideoGame.getProductId())).willReturn(dummyVideoGame);
        //when-then
        mockMvc.perform(get(buildGetUrlWithIdVariable(SHOP_SUBPATH), dummyVideoGame.getProductId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void get_whenHandMadeExists_thenResponseIs200() throws Exception {
        //given
        givenDummyHandMade();
        given(defaultProductService.getProductById(dummyHandMade.getProductId())).willReturn(dummyHandMade);
        //when-then
        mockMvc.perform(get(buildGetUrlWithIdVariable(SHOP_SUBPATH), dummyHandMade.getProductId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void get_whenCategoryExists_thenResponseIs200() throws Exception {
        //  List<Product> testList= new ArrayList<Product>();
        //given
        givenDummyCategory();


        for(int i=0;i<listCategory.size();i++){
            List<Product> testList= new ArrayList<Product>();
            String target=listCategory.get(i);

            switch(target){
                case "CLOTHING":
                    testList.add(dummyClothing);
                    given(defaultProductService.getProductsByCategory(target)).willReturn(testList);
                    break;
                case "HANDMADE":
                    testList.add(dummyHandMade);
                    given(defaultProductService.getProductsByCategory(target)).willReturn(testList);
                    break;
                case "VIDEO_GAMES":
                    testList.add(dummyVideoGame);
                    given(defaultProductService.getProductsByCategory(target)).willReturn(testList);
                    break;
                case "CELL_PHONES":
                    testList.add(dummyCellPhone);
                    given(defaultProductService.getProductsByCategory(target)).willReturn(testList);
                    break;


            }

            //when-then
            mockMvc.perform(get(buildGetUrlWithCategoryVariable(SHOP_SUBPATH), target)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());



        }


    }



   @Test
    public void post_whenClothingIsValid_thenResponseIs201() throws Exception {
        // given

        givenDummyClothing();
        doNothing().when(defaultClothingService).addClothing(dummyClothing);
        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"CLOTHING").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyClothing)))
                .andExpect(status().isCreated());
    }

    @Test
    public void post_whenHandMadeIsValid_thenResponseIs201() throws Exception {
        // given

        givenDummyHandMade();
        doNothing().when(defaultHandMadeService).addHandMade(dummyHandMade);
        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"HANDMADE").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyHandMade)))
                .andExpect(status().isCreated());
    }

    @Test
    public void post_whenVideoGameIsValid_thenResponseIs201() throws Exception {
        // given

        givenDummyVideogame();
        doNothing().when(defaultVideoGameService).addVideoGame(dummyVideoGame);
        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"VIDEOGAME").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyVideoGame)))
                .andExpect(status().isCreated());
    }

    @Test
    public void post_whenCellPhoneIsValid_thenResponseIs201() throws Exception {
        // given

        givenDummyCellPhone();
        doNothing().when(defaultCellPhoneService).addCellPhone(dummyCellPhone);
        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"CELLPHONE").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyCellPhone)))
                .andExpect(status().isCreated());
    }


    @Test
    public void post_whenClothingHasMissingId_thenResponseIs400() throws Exception {
        // given
        dummyClothing = new Clothing(null,"Socks",30,"very elegant", Product.Categories.CLOTHING,"L","Valentino","black");
       // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultClothingService).addClothing(dummyClothing);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"CLOTHING").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyClothing)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenCellPhoneHasMissingId_thenResponseIs400() throws Exception {
        // given
        dummyCellPhone = new CellPhone(null,"Huawei",500.33,"Goog Quality respect price", Product.Categories.CELL_PHONES,"Android","Camera 5x","i7","F55Px",2);
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultCellPhoneService).addCellPhone(dummyCellPhone);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"CELLPHONE").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyCellPhone)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenHandMadeHasMissingId_thenResponseIs400() throws Exception {
        // given
        dummyHandMade = new HandMade(null,"earrings",110.36,"All the component are worked with hands", Product.Categories.HANDMADE,"original handmade product","Germany");
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultHandMadeService).addHandMade(dummyHandMade);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"HANDMADE").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyHandMade)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenVideoGameHasMissingId_thenResponseIs400() throws Exception {
        // given
        dummyVideoGame = new VideoGame(null,"Call of Duty",50.9,"Amazing War game", Product.Categories.VIDEO_GAMES,"Playstation 3", "ff");
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultVideoGameService).addVideoGame(dummyVideoGame);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"VIDEOGAME").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyVideoGame)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenClothingHasMissingCategory_thenResponseIs400() throws Exception {
        // given
        dummyClothing = new Clothing(1L,"Socks",30,"very elegant",null,"L","Valentino","black");
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultClothingService).addClothing(dummyClothing);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"CLOTHING").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyClothing)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenCellPhoneHasMissingCategory_thenResponseIs400() throws Exception {
        // given
        dummyCellPhone = new CellPhone(1L,"Huawei",500.33,"Goog Quality respect price", null,"Android","Camera 5x","i7","F55Px",2);
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultCellPhoneService).addCellPhone(dummyCellPhone);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"CELLPHONE").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyCellPhone)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenHandMadeHasMissingCategory_thenResponseIs400() throws Exception {
        // given
        dummyHandMade = new HandMade(1L,"earrings",110.36,"All the component are worked with hands", null,"original handmade product","Germany");
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultHandMadeService).addHandMade(dummyHandMade);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"HANDMADE").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyHandMade)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_whenVideoGameHasMissingCategory_thenResponseIs400() throws Exception {
        // given
        dummyVideoGame = new VideoGame(1L,"Call of Duty",50.9,"Amazing War game", null,"Playstation 3", "ff");
        // MovieDTO invalidMovieDTO = new MovieDTO(null, "title", "description", null);
        doNothing().when(defaultVideoGameService).addVideoGame(dummyVideoGame);

        // when-then
        mockMvc.perform(post(buildPostUrl(SHOP_SUBPATH,"VIDEOGAME").toLowerCase())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummyVideoGame)))
                .andExpect(status().isBadRequest());
    }










    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildGetUrlWithIdVariable(String subPath) {
        return new StringBuilder()
                .append(BASE_URL)
                .append("/")
                .append(subPath)
                .append("/{productId}")
                .toString();

    }

    private String buildGetUrlWithCategoryVariable(String subPath) {
        return new StringBuilder()
                .append(BASE_URL)
                .append("/")
                .append(subPath)
                .append("/category/{category}")
                .toString();

    }

    private String buildPostUrl(String subPath, String subCategory) {
        return new StringBuilder()
                .append(BASE_URL)
                .append("/")
                .append(subPath)
                .append("/")
                .append(subCategory)
                .toString();
    }
    private void givenDummyClothing() {
        dummyClothing = new Clothing(121L,"Socks",30,"very elegant", Product.Categories.CLOTHING,"L","Valentino","black");
    }

    private void givenDummyCellPhone() {
        dummyCellPhone = new CellPhone(112L,"Huawei",500.33,"Goog Quality respect price", Product.Categories.CELL_PHONES,"Android","Camera 5x","i7","F55Px",2);
    }

    private void givenDummyHandMade() {
        dummyHandMade = new HandMade(114L,"earrings",110.36,"All the component are worked with hands", Product.Categories.HANDMADE,"original handmade product","Germany");
    }

    private void givenDummyVideogame() {
        dummyVideoGame = new VideoGame(13L,"Call of Duty",50.9,"Amazing War game", Product.Categories.VIDEO_GAMES,"Playstation 3", "ff");
    }
    private void givenDummyCategory(){

        listCategory.add("VIDEO_GAMES");
        listCategory.add("CLOTHING");
        listCategory.add("CELL_PHONES");
        listCategory.add("HANDMADE");

        givenDummyHandMade();
        givenDummyCellPhone();
        givenDummyClothing();
        givenDummyVideogame();
    }
}
