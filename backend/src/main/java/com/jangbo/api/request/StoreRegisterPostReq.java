package com.jangbo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


/**
 * 상점 등록 API ([POST] /api/v2/store) 요청에 필요한 리퀘스트 바디 정의.
 */

@Getter
@Setter
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

    @ApiModelProperty(name = "프로필사진", example = "http://경로/프로필.jpg")
    String storeImg;


    @Builder
    public StoreRegisterPostReq(String storeName, String storeCategory, String storePhone, String storeAddr, Integer marketNo, Integer sellerNo, String storeImg) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storePhone = storePhone;
        this.storeAddr = storeAddr;
        this.marketNo = marketNo;
        this.sellerNo = sellerNo;
        this.storeImg=storeImg;
    }


}
