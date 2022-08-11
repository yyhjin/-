package com.jangbo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@ApiModel("StoreEditPatchRequest")
public class StoreEditPatchReq {


    @ApiModelProperty(name = "상점이름", example = "재승이네 청과물")
    String storeName;

    @ApiModelProperty(name = "업종", example = "과일/청과")
    String storeCategory;

    @ApiModelProperty(name = "상점전화번호", example = "010-0000-6603")
    String storePhone;

    @ApiModelProperty(name = "시장번호", example = "1")
    Integer marketNo;

    @ApiModelProperty(name = "상점주소", example = "인천광역시 저쩌구")
    String storeAddr;


    @Builder
    public StoreEditPatchReq(String storeName, String storeCategory, String storePhone, String storeAddr, Integer marketNo) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
        this.marketNo = marketNo;


    }


}
