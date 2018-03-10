package com.quad.ws.shop.web.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "VG")
public class VideoGame extends Product {
    private static final Logger log = LoggerFactory.getLogger(VideoGame.class);
    @Column(name = "PLATFORM")
    @Getter  @Setter
    private String platform;

    @Column(name = "PLOT")
    @Getter  @Setter
    private String plot;

    public VideoGame(Long productId, String name, double price, String description, Categories category, String platform, String plot){
        super(productId,name,price,description,category);

        this.platform=platform;
        this.plot=plot;

    }
}
