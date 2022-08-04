package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeNo;

    @NotBlank
    @Column(length = 30)
    private String storeName;
    @NotBlank
    @Column(length = 30)
    private String storeCategory;
    @NotBlank
    @Column(length = 20)
    private String storePhone;
    @NotBlank
    @Column(length = 150)
    private String storeAddr;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "market_no")
    private Market market;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "seller_no")
    private Seller seller;

    @Column(length = 90)
    private String storeSubject;
    @Column(length = 300)
    private String storeIntro;
    private String storeImg;

    @Builder
    public Store(String storeName, String storeCategory, String storePhone, String storeAddr,Seller seller,Market market,String storeSubject,String storeIntro, String storeImg) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
        this.storeSubject = storeSubject;
        this.storeIntro = storeIntro;
        this.storeImg = storeImg;
        this.seller = seller;
        this.market =market;

    }

    public void update(String storeName, String storeCategory,String storePhone,String storeAddr) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
    }


}
