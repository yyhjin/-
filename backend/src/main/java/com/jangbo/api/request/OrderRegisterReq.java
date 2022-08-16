package com.jangbo.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRegisterReq {
    private List<OrderItemsDto> products;
}
