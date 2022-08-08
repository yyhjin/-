package com.jangbo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 상점 등록 API ([POST] /api/v2/store) 요청에 필요한 리퀘스트 바디 정의.
 */

@Getter
@NoArgsConstructor
@ApiModel("StoreRegisterPostRequest")
public class StoreRegisterPostReq {


    @ApiModelProperty(name = "상점이름", example = "경무의생선가게")
    String storeName;

    @ApiModelProperty(name = "업종", example = "생선")
    String storeCategory;

    @ApiModelProperty(name = "상점전화번호", example = "010-0000-0000")
    String storePhone;

    @ApiModelProperty(name = "상점주소", example = "서울특별시 어쩌구")
    String storeAddr;

    @ApiModelProperty(name = "시장번호", example = "4321")
    Integer marketNo;

    @ApiModelProperty(name = "판매자번호", example = "1234")
    Integer sellerNo;


//    @Builder
//    public StoreRegisterPostReq(String storeName, String storeCategory, String storePhone, String storeAddr, Integer sellerNo) {
//        this.storeName = storeName;
//        this.storeCategory = storeCategory;
//        this.storePhone = storePhone;
//        this.storeAddr = storeAddr;
////        this.marketNo = marketNo;
//        this.sellerNo = sellerNo;
//    }

//    public Store toEntity() {
//        return Store.builder()
//
//                .storeName(storeName)
//                .storeCategory(storeCategory)
//                .storeAddr(storeAddr)
//                .storePhone(storePhone)
//                .build();
//    }






}
