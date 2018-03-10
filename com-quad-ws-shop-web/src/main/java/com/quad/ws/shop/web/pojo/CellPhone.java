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
@DiscriminatorValue(value = "CP")
public class CellPhone extends Product {

    @Column(name = "OPERATING_SYSTEM")
    @Getter @Setter
    private String operatingSystem;

    @Column(name = "DISPLAY")
    @Getter @Setter
    private String display;

    @Column(name = "PROCESSOR")
    @Getter @Setter
    private String processor;

    @Column(name = "CAMERA")
    @Getter @Setter
    private String camera;

    @Column(name = "WARRANTY_PERIOD")
    @Getter @Setter
    private int warrantyPeriod;

    public CellPhone(Long productId, String name, double price, String description, Categories category, String operatingSystem, String display, String processor, String camera, int WarrentyPeridod){
        super(productId,name,price,description,category);
        this.operatingSystem=operatingSystem;
        this.display=display;
        this.processor=processor;
        this.camera=camera;
        this.warrantyPeriod=warrantyPeriod;
    }
}
