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
    @ApiOperation(value = "판매자 회원가입" , notes="판매자 정보를 등록한다.",httpMethod = "POST")
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

//    @GetMapping("/seller/{seller_id}")
//    public SearchSellerResult SearchSeller(@PathVariable("seller_id") String sellerId) {
//        List<Seller> result = sellerRepository.findBySellerId(sellerId);
//        result.map(m -> new SellerDto(m.getBusinessNumber(), m.getSellerName()))
//        if (result.isEmpty()) {
//            return new SearchSellerResult(true, null);
//        } else {
//            return new SearchSellerResult(false, );
//        }
//    }

//    @Data
//    @AllArgsConstructor
//    static class SearchSellerResult<T> {
//        private boolean idcheck;
//        private T data;
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class SellerDto {
//        private String businessNumber;
//        private String sellerName;
//        private String sellerPhone;
//    }




    @Data
    @AllArgsConstructor
    static class CheckSellerIdResponse {
        private boolean idcheck;
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
