package com.jangbo.api.response;

import com.jangbo.db.entity.Store;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

    @Getter
    @ApiModel("RoomInfoResponse ")
    public class RoomInfoRes {
        @ApiModelProperty(name = "방제목", example = "생선가게어서오세요")
        String storeSubject;

        @ApiModelProperty(name = "방소개", example = "생선이 아주 쌉니다 들어오세요 ")
        String storeIntro;

//        @ApiModelProperty(name = "이미지", example = "넣지않으면 프로필이미지가 들어갑니다")
//        String storeImg;


        public RoomInfoRes(Store store) {
            this.storeSubject=store.getStoreSubject();
            this.storeIntro = store.getStoreIntro();
            //this.storeImg=getStoreImg();
        }

    }
