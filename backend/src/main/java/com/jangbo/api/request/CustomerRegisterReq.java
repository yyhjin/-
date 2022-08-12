package com.jangbo.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerRegisterReq{

    private String customerId;
    private String customerName;
    private String customerNickname;
    private String customerAddr;
    private String customerPhone;
    private String customerPwd;

}
