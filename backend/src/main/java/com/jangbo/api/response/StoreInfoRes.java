package com.jangbo.api.response;

import com.jangbo.db.entity.Store;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("StoreResponse ")
public class StoreInfoRes {
    @ApiModelProperty(name = "상점번호", example = "1")
    Integer storeNo;

    @ApiModelProperty(name = "상점이름", example = "경무의생선가게")
    String storeName;

    @ApiModelProperty(name = "업종", example = "생선")
    String storeCategory;

    @ApiModelProperty(name = "상점전화번호", example = "010-0000-0000")
    String storePhone;

    @ApiModelProperty(name = "상점주소", example = "서울특별시 어쩌구")
    String storeAddr;

    public StoreInfoRes(Store store) {
        this.storeNo = store.getStoreNo();
        this.storeName = store.getStoreName();
        this.storeCategory = store.getStoreCategory();
        this.storePhone = store.getStorePhone();
        this.storeAddr = store.getStoreAddr();
    }
}
