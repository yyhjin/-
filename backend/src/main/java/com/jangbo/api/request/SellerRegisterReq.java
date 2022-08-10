package com.jangbo.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerRegisterReq {

    private String sellerId;

    private String businessNumber;

    private String sellerName;

    private String sellerPhone;

    private String sellerPwd;
}
