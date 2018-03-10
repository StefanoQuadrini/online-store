package com.quad.ws.shop.web.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor
@Entity
@Inheritance
@DiscriminatorColumn(name = "product_type")
@Table(name = "products")

public abstract class Product  {

    @Id
    @NotNull
    @Column(unique = true, name = "PRODUCT_ID")
    @Getter @Setter
    private Long productId;

    @NotNull @NotBlank @NotEmpty
    @Column(name = "NAME")
    @Getter @Setter
    private String name;


    @Column(name = "PRICE")
    @Getter @Setter
    private double price;

    @Column(name = "DESCRIPTION")
    @Getter @Setter
    private String description;

    @Column(name = "CATEGORIES")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Categories categories;

    public enum Categories {

        VIDEO_GAMES,
        CELL_PHONES,
        CLOTHING,
        HANDMADE

    }


}


