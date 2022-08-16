package com.jangbo.db.dto;


import com.jangbo.db.entity.CallOrder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CallOrderDto {
    private Integer orderNo;
    private String customerId;
    private Integer storeNo;

    public CallOrderDto(CallOrder callOrder) {
        this.orderNo = callOrder.getOrderNo();
        this.customerId = callOrder.getCustomerId();
        this.storeNo = callOrder.getStoreNo();
    }
}