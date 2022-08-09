package com.jangbo.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderStateEditReq {

    private Integer orderNo;
    private String status;
}
