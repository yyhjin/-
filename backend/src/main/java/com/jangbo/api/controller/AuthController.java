package com.jangbo.api.controller;

import com.jangbo.api.request.CustomerRegisterReq;
import com.jangbo.api.request.SellerRegisterReq;
import com.jangbo.api.response.Response;
import com.jangbo.api.service.Auth.AuthService;
import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Seller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "권한api", tags={"권한"})
@Slf4j
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "회원가입 - 판매자", notes="판매자로 가입한다.",httpMethod = "POST")
    @PostMapping("/seller/signup")
    public Response signUpSeller(@RequestBody @Valid SellerRegisterReq sellerRegisterReq) {
        try {
            authService.signUpSeller(sellerRegisterReq);
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", null);
        } catch (Exception e) {
            return new Response("error", e.toString(), null);
        }
    }

    @ApiOperation(value = "회원가입 - 소비자", notes="소비자로 가입한다.",httpMethod = "POST")
    @PostMapping("/customer/signup")
    public Response signUpCustomer(@RequestBody @Valid CustomerRegisterReq customerRegisterReq) {
        try {
            authService.signUpCustomer(customerRegisterReq);
            return new Response("success", "소비자 회원가입을 성공적으로 완료했습니다.", null);
        } catch (Exception e) {
            return new Response("error", e.toString(), null);
        }
    }

//    @Data
//    @AllArgsConstructor
//    static class CreateCustomerRequest {
//        private String customerId;
//        private String customerName;
//        private String customerNickname;
//        private String customerAddr;
//        private String customerPhone;
//        private String customerPwd;
//    }

    //    @ApiOperation(value = "회원가입 - 판매자", notes="판매자로 가입한다.",httpMethod = "POST")
//    @PostMapping("/seller/signup")
//    public Response signUpSeller(@Valid CreateSellerRequest request) {
//        try {
//            Seller seller = new Seller();
//            seller.setSellerId(request.getSellerId());
//            seller.setSellerName(request.getSellerName());
//            seller.setBusinessNumber(request.getBusinessNumber());
//            seller.setSellerPhone(request.getSellerPhone());
//            seller.setSellerPwd(request.getSellerPwd());
//            authService.signUpSeller(seller);
//            return new Response("success", "판매자 회원가입을 성공적으로 완료했습니다.", null);
//        } catch (Exception e) {
//            return new Response("error", "판매자 회원가입을 하는 도중 오류가 발생했습니다.", null);
//        }
//    }
}
