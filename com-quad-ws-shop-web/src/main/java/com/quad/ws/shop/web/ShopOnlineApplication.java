package com.quad.ws.shop.web;

import com.quad.ws.shop.web.daos.CellPhoneDao;
import com.quad.ws.shop.web.daos.ClothingDao;
import com.quad.ws.shop.web.daos.HandMadeDao;
import com.quad.ws.shop.web.daos.VideoGameDao;
import com.quad.ws.shop.web.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ShopOnlineApplication implements CommandLineRunner{


    @Autowired
    CellPhoneDao cellPhoneDao;
    @Autowired
    ClothingDao clothingDao;

    @Autowired
    HandMadeDao handMadeDao;

    @Autowired
    VideoGameDao videoGameDao;


    private static final Logger log= LoggerFactory.getLogger(ShopOnlineApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ShopOnlineApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info(" ---- Database initialization -----");

        videoGameDao.save(new VideoGame(1L,"Call of Duty",50.9,"Amazing War game", Product.Categories.VIDEO_GAMES,"Playstation 3", "world war history"));
        videoGameDao.save(new VideoGame(5L,"Battlefield",100,"Fantascientific War game", Product.Categories.VIDEO_GAMES,"Playstation 4", "Galactic war"));
        cellPhoneDao.save(new CellPhone(2L,"Huawei",250.33,"Goog Quality respect price", Product.Categories.CELL_PHONES,"Android","Camera 5x","Cortex-A73","Camera 5x",3));
        clothingDao.save(new Clothing(3L,"Jacket",400,"very elegant", Product.Categories.CLOTHING,"XL","Valentino","black"));
        handMadeDao.save(new HandMade(4L,"earrings",110.36,"All the component are worked with hands", Product.Categories.HANDMADE,"original handmade product","Germany"));
    }
}
