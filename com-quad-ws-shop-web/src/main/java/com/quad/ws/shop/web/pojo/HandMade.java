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
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "HM")
public class HandMade extends Product {
    @Column(name = "TYPE")
    @Getter @Setter
    private String type;

    @Column(name = "MADE_IN")
    @Getter @Setter
    private String madeIN;



    public HandMade(Long productId, String name, double price, String description, Categories category,String type, String madeIN){
        super(productId,name,price,description,category);
        this.type=type;
        this.madeIN=madeIN;
    }
}
