package com.jangbo.api.response;

import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.entity.Store;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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

    @ApiModelProperty(name = "판매자번호", example = "1234")
    Seller seller;

    @ApiModelProperty(name = "시장번호", example = "4321")
    Market market;

    @ApiModelProperty(name = "이미지파일명", example = "싱글생글-1-20220809-231040.jpg") //상점이름+상점번호+등록한 날짜시간
    String storeImg;


    public StoreInfoRes(Store store) {
        this.storeNo = store.getStoreNo();
        this.storeName = store.getStoreName();
        this.storeCategory = store.getStoreCategory();
        this.storePhone = store.getStorePhone();
        this.storeAddr = store.getStoreAddr();
        this.storeImg = store.getStoreImg();
        this.seller = seller.builder()
                .sellerNo(store.getSeller().getSellerNo()).build();
        this.market = market.builder().marketNo(store.getMarket().getMarketNo()).build();

    }

    @Getter
    static class Seller {
        private Integer sellerNo;

        @Builder
        public Seller(Integer sellerNo) {
            this.sellerNo = sellerNo;
        }
    }

    @Getter
    public static class Market {
        private Integer marketNo;

        @Builder
        public Market(Integer marketNo) {
            this.marketNo = marketNo;
        }
    }


}
