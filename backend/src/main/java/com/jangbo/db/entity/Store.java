package com.jangbo.db.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue
    @JoinColumn(name = "store_no")
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

//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "business_number")
//    private Seller seller;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "market_no")
    private Market market;

    @Column(length = 300)
    private String storeIntro;
    @Column(length = 90)
    private String storeSubject;
    private String storeImg;

//    @OneToMany(mappedBy = "store")
//    private List<Item> items = new ArrayList<>();
//

    //==생성 메서드==//
//    public static Store createStore(String storeName, String storeCategory, String storePhone, String storeAddr) {
//        Store store = new Store();
//        store.setStoreName(storeName);
//        store.setStoreCategory(storeCategory);
//        store.setStorePhone(storePhone);
//        store.setStoreAddr(storeAddr);
//
//        return store;
//    }



}
