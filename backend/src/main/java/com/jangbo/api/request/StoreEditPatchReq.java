package com.jangbo.api.request;


import com.jangbo.db.entity.Seller;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@ApiModel("StoreEditPatchRequest")
public class StoreEditPatchReq {


    @ApiModelProperty(name = "상점이름", example = "재승이네 청과물")
    String storeName;

    @ApiModelProperty(name = "업종", example = "과일/청과")
    String storeCategory;

    @ApiModelProperty(name = "상점전화번호", example = "010-0000-6603")
    String storePhone;

    @ApiModelProperty(name = "상점주소", example = "인천광역시 저쩌구")
    String storeAddr;

//    @ApiModelProperty(name = "프로필사진", example = "profile.jpg")
//    File file;
//    @Builder
//    public StoreEditPatchReq(String storeName, String storeCategory, String storePhone, String storeAddr) {
//        this.storeName = storeName;
//        this.storeCategory = storeCategory;
//        this.storePhone = storePhone;
//        this.storeAddr = storeAddr;
//
//
//    }



}