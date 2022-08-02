package com.jangbo.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "market_no")
    private Market market;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(length = 90)
    private String storeSubject;
    @Column(length = 300)
    private String storeIntro;
    private String storeImg;

    @Builder
    public Store(String storeName, String storeCategory, String storePhone, String storeAddr,Market market,Seller seller,String storeSubject,String storeIntro, String storeImg) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
        this.market = market;
        this.seller = seller;
        this.storeSubject = storeSubject;
        this.storeIntro = storeIntro;
        this.storeImg = storeImg;
    }


}
