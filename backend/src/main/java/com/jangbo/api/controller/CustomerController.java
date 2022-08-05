package com.jangbo.api.controller;

import com.jangbo.api.service.CustomerService;
import com.jangbo.db.entity.*;
import com.jangbo.db.repository.CustomerRepository;
import com.jangbo.db.repository.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Api(value = "소비자api", tags={"소비자"})
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    private final StoreRepository storeRepository;

    //API 정상 작동하는지 확인 후 추가
//    @ApiOperation(value = "회원가입" , notes="소비자 정보를 등록한다.",httpMethod = "POST")
//    @PostMapping("/customer/signup")
//    public CreateUpdateCustomerResponse saveCustomer(@Valid CreateUpdateCustomerRequest request) {
//        Customer customer = new Customer();
//        customer.setCustomerId(request.getCustomerId());
//        customer.setCustomerName(request.getCustomerName());
//        customer.setCustomerNickname(request.getCustomerNickname());
//        customer.setCustomerAddr(request.getCustomerAddr());
//        customer.setCustomerPhone(request.getCustomerPhone());
//
//        Integer customerNo = customerService.join(customer);
//        return new CreateUpdateCustomerResponse(customerNo);
//    }

    @ApiOperation(value = "아이디 중복 검사", notes="판매자 아이디를 중복 검사한다. 중복이 안되면 true, 중복이면 false",httpMethod = "GET")
    @GetMapping("/customer/idcheck/{customer_id}")
    public CheckCustomerIdResponse IdCheck(
            @PathVariable("customer_id") String customerId
    ) {
        if (customerRepository.findByCustomerId(customerId) == null) {
            return new CheckCustomerIdResponse(true);
        } else {
            return new CheckCustomerIdResponse(false);
        }
    }

    //회원정보 조회 - 개인정보
    @ApiOperation(value = "회원정보 조회 - 개인정보", notes="소비자 회원정보를 조회한다.",httpMethod = "GET")
    @GetMapping("/customer/{customer_no}")
    public CustomerRequest SearchSeller(@PathVariable("customer_no") Integer customerNo) {
        Customer customer = customerRepository.findOne(customerNo);
        if (isNull(customer)) {
            return new CustomerRequest(null, null, null, null, null);
        } else {
            return new CustomerRequest(customer.getCustomerId(), customer.getCustomerName(),customer.getCustomerNickname(), customer.getCustomerAddr(), customer.getCustomerPhone());
        }
    }

    @ApiOperation(value = "회원정보 수정", notes="소비자 회원정보를 수정한다.",httpMethod = "PUT")
    @PutMapping("/customer/{customer_no}")
    public boolean updateCustomer(
            @PathVariable("customer_no") Integer customerNo,
            @Valid CustomerRequest request
    ) {
        return customerService.update(customerNo, request.getCustomerName(), request.getCustomerNickname(), request.getCustomerAddr(), request.getCustomerPhone());
    }

//    회원정보 조회 - 찜목록
    @ApiOperation(value = "회원정보 조회 - 찜목록", notes = "소비자 찜목록을 조회한다.", httpMethod = "GET")
    @GetMapping("/customer/{customer_no}/interstore")
    public List<InterStoreDto> findInterStores(@PathVariable("customer_no") Integer customerNo) {
        List<InterStore> interStores = customerRepository.findOne(customerNo).getInterStores();
        List<InterStoreDto> result = interStores.stream()
                .map(i -> new InterStoreDto(i))
                .collect(Collectors.toList());
        return result;
    }


    //회원정보 조회 - 주문내역 조회
    @ApiOperation(value = "회원정보 조회 - 주문내역 조회", notes = "소비자 주문내역을 조회한다.", httpMethod = "GET")
    @GetMapping("/customer/{customer_no}/orders")
    public List<OrdersDto> findOrders(@PathVariable("customer_no") Integer customerNo) {
        List<Orders> orders = customerRepository.findOne(customerNo).getOrders();
        List<OrdersDto> result = orders.stream()
                .map(o -> new OrdersDto(o.getOrderDate(),
                                        o.getStatus(),
                                        storeRepository.getReferenceById(o.getStoreNo()).getStoreName(),
                                        o.getOrderItems().stream().map(oi -> new OrderItemDto(oi.getItemName(),oi.getCount(), oi.getPrice())
                                        )))
                .collect(Collectors.toList());
        return result;
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
    static class OrdersDto<T> {
        private Integer orderNo;
        private String orderDate;
        private String status;
        private String storeNo;
        private String storeName; // store 에서 가져와야함
        private T order_item; //

        public OrdersDto(Date orderDate, @NotBlank String status, @NotBlank String storeName, Stream<OrderItemDto> orderItemDtoStream) {
        }
    }

    @Data
    @AllArgsConstructor
    static class OrderItemDto {
        private String itemName;
        private Integer count;
        private Integer price;
    }


    @Data
    @AllArgsConstructor
    static class InterStoreDto {
        private Integer storeNo;
        private String storeName;

        public InterStoreDto(InterStore interStore) {
            Store store = interStore.getStore();
            storeNo = store.getStoreNo();
            storeName = store.getStoreName();
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
    static class CheckCustomerIdResponse {
        private boolean idCheck;
    }




    @Data
    @AllArgsConstructor
    static class CreateUpdateCustomerResponse {
        private Integer customerNo;
    }
}
