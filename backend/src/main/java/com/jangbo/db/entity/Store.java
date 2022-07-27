package com.jangbo.db.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    private String storeName;
    @NotEmpty
    private String storeCategory;
    @NotEmpty
    private String storePhone;
    @NotEmpty
    private String storeAddr;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "business_number")
    private Seller seller;

    private String marketNo;

    private String storeIntro;
    private String storeSubject;
    private String storeImg;

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
