package com.jangbo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 아이템 등록 API ([POST] /api/v2/store/item/:store_no) 요청에 필요한 리퀘스트 바디 정의.
 */

@Getter
@NoArgsConstructor
@ApiModel("ItemRegisterPostRequest")
public class ItemRegisterPostReq {

    @ApiModelProperty(name = "아이템이름/단위", example = "사과 한 바구니")
    String itemName;

    @ApiModelProperty(name = "아이템가격", example = "30000")
    Integer price;

    @ApiModelProperty(name = "최근사용여부", example = "true")
    boolean recent = true;

    @ApiModelProperty(name = "상점번호", example = "4")
    Integer storeNo;
}
