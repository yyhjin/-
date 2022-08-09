package com.jangbo.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRegisterReq {

    private Integer itemNo;
    private Integer count;

}
