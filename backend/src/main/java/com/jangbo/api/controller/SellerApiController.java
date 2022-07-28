package com.jangbo.api.controller;

import com.jangbo.api.service.SellerService;
import com.jangbo.db.entity.Seller;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellerApiController {

    private final SellerService sellerService;

    //반환 값 결정할 것
    @PostMapping("/seller/signup")
    public CreateSellerResponse saveSeller(@RequestBody @Valid CreateSellerRequest request) {
        Seller seller = new Seller();
        seller.setSellerId(request.getSellerId());
        seller.setSellerName(request.getSellerName());
        seller.setBusinessNumber(request.getBusinessNumber());
        seller.setSellerPhone(request.getSellerPhone());

        Integer sellerNo = sellerService.join(seller);
        return new CreateSellerResponse(sellerNo);
    }


    @Data
    static class CreateSellerRequest {
        private String sellerId;
        private String sellerName;
        private String businessNumber;
        private String sellerPhone;

    }


    @Data
    static class CreateSellerResponse {
        public CreateSellerResponse(Integer sellerNo) {
            this.sellerNo = sellerNo;
        }
        private Integer sellerNo;
    }
}
