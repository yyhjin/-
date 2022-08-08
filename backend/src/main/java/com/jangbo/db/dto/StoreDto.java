package com.jangbo.db.dto;


import lombok.Data;

@Data
public class StoreDto {
    private Integer storeNo ;
    private String storeName;
    private String storeCategory;
    private String storePhone;
    private String storeAddr;
    private Integer marketNo;
    private Integer sellerNo;
    private String storeSubject;
    private String storeIntro;
    private String storeImg;

    public StoreDto(Integer storeNo, String storeName, String storeCategory, String storeAddr,
                    Integer marketNo, Integer sellerNo) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storeAddr = storeAddr;
        this.marketNo = marketNo;
        this.sellerNo = sellerNo;

    }


}