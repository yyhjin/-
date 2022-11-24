package com.jangbo.api.controller;

import com.jangbo.api.service.SellerService;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.repository.SellerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.isNull;
@Slf4j
@Api(value = "판매자api", tags={"판매자"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/be/seller")
public class SellerController {

    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

    @ApiOperation(value = "아이디 중복 검사", notes="판매자 아이디를 중복 검사한다. 중복이 안되면 true, 중복이면 false",httpMethod = "GET")
    @GetMapping("/idcheck/{seller_id}")
    public CheckResponse idCheck(
            @PathVariable("seller_id") String sellerId
    ) {
        if (sellerRepository.findBySellerId(sellerId) == null) {
            return new CheckResponse(true);
        } else {
            return new CheckResponse(false);
        }
    }

    @ApiOperation(value = "회원정보 조회", notes="판매자 회원정보를 조회한다.",httpMethod = "GET")
    @GetMapping("/{seller_no}")
    public SellerDto SearchSeller(@PathVariable("seller_no") Integer sellerNo) {
        Seller seller = sellerRepository.findOne(sellerNo);
        if (isNull(seller)) {
            return new SellerDto(null, null, null);
        } else {
            return new SellerDto(seller.getBusinessNumber(), seller.getSellerName(),seller.getSellerPhone());
        }
    }

    @ApiOperation(value = "회원정보 수정", notes="판매자 회원정보를 수정한다.",httpMethod = "PUT")
    @PutMapping("/{seller_no}")
    public boolean updateSeller(
            @PathVariable("seller_no") Integer sellerNo,
            @RequestBody @Valid SellerDto request
    ) {
        sellerService.update(sellerNo, request.getBusinessNumber(), request.getSellerName(), request.getSellerPhone());
        return true;
    }

    @ApiOperation(value = "사업자등록번호 중복 검사", notes="사업자등록번호를 중복 검사한다. 중복이 안되면 true, 중복이면 false",httpMethod = "GET")
    @GetMapping("/business_number/{business_number}")
    public CheckResponse businessNumberCheck(
            @PathVariable("business_number") String businessNumber
    ) {
        if (sellerRepository.findByBusinessNumber(businessNumber) == null) {
            return new CheckResponse(true);
        } else {
            return new CheckResponse(false);
        }
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
    static class CheckResponse {
        private boolean check;
    }

    @Data
    static class CreateUpdateSellerResponse {
        public CreateUpdateSellerResponse(Integer sellerNo) {
            this.sellerNo = sellerNo;
        }
        private Integer sellerNo;
    }
}
