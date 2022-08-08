package com.jangbo.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel("ItemRecentPatchRequest")
public class ItemRecentPatchReq {

    @ApiModelProperty(name = "최근사용여부", example = "true")
    boolean recent;
}
