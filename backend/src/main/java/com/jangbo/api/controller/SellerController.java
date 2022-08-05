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

import static java.util.Objects.isNull;

@Api(value = "판매자api", tags={"판매자"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

//    @ApiOperation(value = "회원가입" , notes="판매자 정보를 등록한다.",httpMethod = "POST")
//    @PostMapping("/signup")
//    public CreateUpdateSellerResponse saveSeller(@Valid CreateSellerRequest request) {
//        Seller seller = new Seller();
//        seller.setSellerId(request.getSellerId());
//        seller.setSellerName(request.getSellerName());
//        seller.setBusinessNumber(request.getBusinessNumber());
//        seller.setSellerPhone(request.getSellerPhone());
//
//        Integer sellerNo = sellerService.join(seller);
//        return new CreateUpdateSellerResponse(sellerNo);
//    }

//    @PostMapping("/signup")
//    public Response signUpUser(@RequestBody Seller seller){
//        Response response = new Response();
//
//        try{
//            authSellerService.signUpUser(member);
//            response.setResponse("success");
//            response.setMessage("회원가입을 성공적으로 완료했습니다.");
//        }
//        catch(Exception e){
//            response.setResponse("failed");
//            response.setMessage("회원가입을 하는 도중 오류가 발생했습니다.");
//            response.setData(e.toString());
//        }
//
//        return response;
//    }

    @ApiOperation(value = "아이디 중복 검사", notes="판매자 아이디를 중복 검사한다. 중복이 안되면 true, 중복이면 false",httpMethod = "GET")
    @GetMapping("/idcheck/{seller_id}")
    public CheckSellerIdResponse IdCheck(
            @PathVariable("seller_id") String sellerId
    ) {
        if (sellerRepository.findBySellerId(sellerId) == null) {
            return new CheckSellerIdResponse(true);
        } else {
            return new CheckSellerIdResponse(false);
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
            @Valid SellerDto request
    ) {
        sellerService.update(sellerNo, request.getBusinessNumber(), request.getSellerName(), request.getSellerPhone());
        return true;
    }

//    @Data
//    @AllArgsConstructor
//    static class SellerRequest {
//        private String sellerId;
//        private String sellerName;
//        private String businessNumber;
//        private String sellerPhone;
//    }

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
    static class CreateUpdateSellerResponse {
        public CreateUpdateSellerResponse(Integer sellerNo) {
            this.sellerNo = sellerNo;
        }
        private Integer sellerNo;
    }
}
