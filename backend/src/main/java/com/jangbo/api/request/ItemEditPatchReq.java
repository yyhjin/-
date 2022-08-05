package com.jangbo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@ApiModel("ItemEditPatchRequest")
public class ItemEditPatchReq {

        @ApiModelProperty(name = "아이템이름/단위", example = "사과 한 바구니")
        String itemName;

        @ApiModelProperty(name = "아이템가격", example = "30000")
        Integer price;

}

