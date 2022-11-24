package com.jangbo.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel("RoomEditPatchRequest")
public class RoomEditPatchReq {

    @ApiModelProperty(name = "상점번호", example = "1")
    Integer storeNo;

    @ApiModelProperty(name = "방제목", example = "생선가게어서오세요")
    String storeSubject;

    @ApiModelProperty(name = "방소개", example = "생선이 아주 쌉니다 들어오세요 ")
    String storeIntro;


}
