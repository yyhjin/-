package com.jangbo.db.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.yaml.snakeyaml.error.Mark;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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

    @Column(length = 150)
    private String storeAddr;

    @Column(length = 150)
    private String storeImg; //경로 저장


    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "market_no")
    private Market market;

    @OneToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "seller_no")
    private Seller seller;

    @Column(length = 90)
    private String storeSubject;
    @Column(length = 300)
    private String storeIntro;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean storeIdx = false;

    @Builder
    public Store(Integer storeNo, String storeName, String storeCategory, String storePhone, String storeAddr, String storeImg, Seller seller, Market market, String storeSubject, String storeIntro) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
        this.storeImg = storeImg;
        this.storeSubject = storeSubject;
        this.storeIntro = storeIntro;
        this.seller = seller;
        this.market = market;

    }

    public void updateStore(String storeName, String storeCategory, String storePhone, String storeAddr, Market market) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
        this.market = market;

    }

    public void updateRoom(String storeSubject, String storeIntro) {
        this.storeSubject = storeSubject;
        this.storeIntro = storeIntro;

    }

    public void updateImg(String fileName) {
        this.storeImg = fileName;
    }


    public void updateStoreIdx(boolean storeIdx) {
        this.storeIdx = storeIdx;
    }
}
