package com.jangbo.api.controller;

import com.jangbo.api.request.InterStoreReq;
import com.jangbo.api.service.CustomerService;
import com.jangbo.api.service.InterStoreService;
import com.jangbo.api.service.StoreService;
import com.jangbo.db.entity.*;
import com.jangbo.db.repository.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Api(value = "소비자api", tags={"소비자"})
@RestController
@RequestMapping("/be/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final InterStoreService interStoreService;

    private final StoreRepository storeRepository;

    @ApiOperation(value = "아이디 중복 검사", notes="판매자 아이디를 중복 검사한다. 중복이 안되면 true, 중복이면 false",httpMethod = "GET")
    @GetMapping("/idcheck/{customer_id}")
    public CheckResponse IdCheck(
            @PathVariable("customer_id") String customerId
    ) {
        if (customerService.findByCustomerId(customerId) == null) {
            return new CheckResponse(true);
        } else {
            return new CheckResponse(false);
        }
    }

    //회원정보 조회 - 개인정보
    @ApiOperation(value = "회원정보 조회 - 개인정보", notes="소비자 회원정보를 조회한다.",httpMethod = "GET")
    @GetMapping("/{customer_no}")
    public CustomerRequest SearchSeller(@PathVariable("customer_no") Integer customerNo) {
        Customer customer = customerService.findOne(customerNo);
        if (isNull(customer)) {
            return new CustomerRequest(null, null, null, null, null);
        } else {
            return new CustomerRequest(customer.getCustomerId(), customer.getCustomerName(),customer.getCustomerNickname(), customer.getCustomerAddr(), customer.getCustomerPhone());
        }
    }

    @ApiOperation(value = "회원정보 수정", notes="소비자 회원정보를 수정한다.",httpMethod = "PUT")
    @PutMapping("/{customer_no}")
    public boolean updateCustomer(
            @PathVariable("customer_no") Integer customerNo,
            @RequestBody @Valid CustomerUpdateReq request
    ) {
        return customerService.update(customerNo, request.getCustomerName(), request.getCustomerNickname(), request.getCustomerAddr(), request.getCustomerPhone());
    }

//    회원정보 조회 - 찜목록
    @ApiOperation(value = "회원정보 조회 - 찜목록", notes = "소비자 찜목록을 조회한다.", httpMethod = "GET")
    @GetMapping("/{customer_no}/interstore")
    public List<InterStoreDto> findInterStores(@PathVariable("customer_no") Integer customerNo) {
        List<InterStore> interStores = customerService.findOne(customerNo).getInterStores();
        List<InterStoreDto> result = interStores.stream()
                .map(i -> new InterStoreDto(i, storeRepository.findByStoreNo(i.getStoreNo()).getStoreName(), storeRepository.findByStoreNo(i.getStoreNo()).isStoreIdx()))
                .collect(Collectors.toList());
        return result;
    }


    @ApiOperation(value = "찜 목록 등록", notes = "소비자 상점을 찜 목록에 등록, 삭제한다. true 반환: 등록, false 반환: 삭제", httpMethod = "POST")
    @PostMapping("/{customer_no}/interstore")
    public CheckResponse interstoreUpdate(
            @PathVariable("customer_no") Integer customerNo,
            @RequestBody @Valid InterStoreReq interStoreReq) {
        return new CheckResponse(interStoreService.update(customerNo, interStoreReq));
    }

    @Data
    @AllArgsConstructor
    static class CustomerRequest {
        private String customerId;
        private String customerName;
        private String customerNickname;
        private String customerAddr;
        private String customerPhone;
    }

    @Data
    @AllArgsConstructor
    static class CustomerUpdateReq {
        private String customerName;
        private String customerNickname;
        private String customerAddr;
        private String customerPhone;
    }


    @Data
    @AllArgsConstructor
    static class InterStoreDto {
        private Integer storeNo;
        private String storeName;

        private boolean storeIdx;

        public InterStoreDto(InterStore interStore, String storeName, boolean storeIdx) {
            this.storeNo = interStore.getStoreNo();
            this.storeName = storeName;
            this.storeIdx = storeIdx;
        }

    }


    @Data
    @AllArgsConstructor
    static class CustomerDto {
        private String businessNumber;
        private String sellerName;
        private String sellerPhone;
    }


    @Data
    @AllArgsConstructor
    static class CheckResponse {
        private boolean Check;
    }




    @Data
    @AllArgsConstructor
    static class CreateUpdateCustomerResponse {
        private Integer customerNo;
    }
}
