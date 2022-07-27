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
    private Long storeNo;

    @NotBlank
    private String storeName;
    @NotBlank
    private String storeCategory;
    @NotBlank
    private String storePhone;
    @NotBlank
    private String storeAddr;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "business_number")
    private Seller seller;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "market_no")
    private Market market;

    private String storeIntro;
    private String storeSubject;
    private String storeImg;

    @OneToMany(mappedBy = "store")
    private List<Item> items = new ArrayList<>();


    //==생성 메서드==//
    public static Store createStore(String storeName, String storeCategory, String storePhone, String storeAddr) {
        Store store = new Store();
        store.setStoreName(storeName);
        store.setStoreCategory(storeCategory);
        store.setStorePhone(storePhone);
        store.setStoreAddr(storeAddr);

        return store;
    }



}
