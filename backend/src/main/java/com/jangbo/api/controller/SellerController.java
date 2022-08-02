package com.jangbo.api.controller;

import com.jangbo.api.service.SellerService;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.repository.SellerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "판매자api", tags={"판매자"})
@RestController
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

    //API 정상 작동하는지 확인 후 추가
    @ApiOperation(value = "회원가입" , notes="판매자 정보를 등록한다.",httpMethod = "POST")
    @PostMapping("/seller/signup")
    public CreateSellerResponse saveSeller(@Valid CreateSellerRequest request) {
        Seller seller = new Seller();
        seller.setSellerId(request.getSellerId());
        seller.setSellerName(request.getSellerName());
        seller.setBusinessNumber(request.getBusinessNumber());
        seller.setSellerPhone(request.getSellerPhone());

        Integer sellerNo = sellerService.join(seller);
        return new CreateSellerResponse(sellerNo);
    }

    @ApiOperation(value = "아이디 중복 검사", notes="판매자 아이디를 중복 검사한다. 중복이 안되면 true, 중복이면 false",httpMethod = "GET")
    @GetMapping("/seller/idcheck/{seller_id}")
    public CheckSellerIdResponse IdCheck(
            @PathVariable("seller_id") String sellerId
    ) {
        if (sellerRepository.findBySellerId(sellerId).isEmpty()) {
            return new CheckSellerIdResponse(true);
        } else {
            return new CheckSellerIdResponse(false);
        }
    }

    @ApiOperation(value = "회원정보 조회", notes="판매자 회원정보를 조회한다.",httpMethod = "GET")
    @GetMapping("/seller/{seller_id}")
    public SearchSellerResult SearchSeller(@PathVariable("seller_id") String sellerId) {
        List<Seller> result = sellerRepository.findBySellerId(sellerId);
        if (result.isEmpty()) {
            return new SearchSellerResult(true, null);
        } else {
            Seller seller = result.get(0);
            SellerDto sellerDto = new SellerDto(seller.getBusinessNumber(), seller.getSellerName(),seller.getSellerPhone());
            return new SearchSellerResult(false, sellerDto);
        }
    }

    @Data
    @AllArgsConstructor
    static class SearchSellerResult<T> {
        private boolean idCheck;
        private T sellerData;
    }

    @Data
    @AllArgsConstructor
    static class SellerDto {
        private String businessNumber;
        private String sellerName;
        private String sellerPhone;
    }


    @Data
    @AllArgsConstructor
    static class CheckSellerIdResponse {
        private boolean idCheck;
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
