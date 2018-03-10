package com.quad.ws.shop.web.controllers;


import com.quad.ws.shop.web.pojo.*;
import com.quad.ws.shop.web.services.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/quad/ws")
public class EntryController {

    @Autowired
    ProductService productService;

    @Autowired
    VideoGameService videoGameService;

    @Autowired
    HandMadeService handMadeService;

    @Autowired
    ClothingService clothingService;

    @Autowired
    CellPhoneService cellPhoneService;

    private static final Logger log = LoggerFactory.getLogger(EntryController.class);

    @RequestMapping(value ="/product")
    @ResponseBody
    public String getProduct(){
        return "Quad product";
    }

    @RequestMapping(value = "/shop/{productId}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }


    @RequestMapping(value = "/shop/category/{category}", method = RequestMethod.GET)
    public List<Product> getProductsByCategory(@PathVariable("category") String category) {
        return productService.getProductsByCategory(category);
    }

   @RequestMapping(value = "/shop/videogame", method = RequestMethod.POST)
    public void addVideoGamet(@Valid @RequestBody VideoGame videoGame, HttpServletResponse response) {
        videoGameService.addVideoGame(videoGame);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @RequestMapping(value = "/shop/handmade", method = RequestMethod.POST)
    public void addHandMade(@Valid @RequestBody HandMade handMade, HttpServletResponse response) {
        handMadeService.addHandMade(handMade);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @RequestMapping(value = "/shop/clothing", method = RequestMethod.POST)
    public void addClothing(@Valid @RequestBody Clothing clothing, HttpServletResponse response) {
        clothingService.addClothing(clothing);
        response.setStatus(HttpStatus.CREATED.value());
    }



    @RequestMapping(value = "/shop/cellphone", method = RequestMethod.POST)
    public void addCellPhone(@Valid @RequestBody CellPhone cellPhone, HttpServletResponse response) {
        cellPhoneService.addCellPhone(cellPhone);
        response.setStatus(HttpStatus.CREATED.value());
    }



}
