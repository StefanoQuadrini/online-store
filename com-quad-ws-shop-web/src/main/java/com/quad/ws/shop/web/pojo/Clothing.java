package com.quad.ws.shop.web.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "CL")
public class Clothing extends Product {

    @NotNull
    @Column(name = "SIZE")
    @Getter @Setter
    private String size;

    @Column(name = "BRAND")
    @Getter @Setter
    private String brand;

    @Column(name = "COLOUR")
    @Getter @Setter
    private String colour;

    public Clothing(Long productId, String name, double price, String description, Categories category, String size, String brand, String colour){
        super( productId,name,price,description,category);
        this.size=size;
        this.brand=brand;
        this.colour=colour;
    }


}
