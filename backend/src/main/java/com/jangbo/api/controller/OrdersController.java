package com.jangbo.api.controller;

import com.jangbo.api.service.CustomerService;
import com.jangbo.api.service.OrdersService;
import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.entity.Customer;
import com.jangbo.db.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "주문 api", tags={"주문"})
@RestController
@RequestMapping("order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    CustomerRepository customerRepository;

    @ApiOperation(value = "주문목록 조회" , notes="소비자번호로 해당 소비자의 주문목록을 조회한다.",httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllByCustomerNo(Integer customerno) {
        List<OrdersDto> orders = ordersService.findOrdersByCustomer_CustomerNo(customerno);
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "첫주문여부 조회" , notes="소비자번호로 해당 소비자의 첫 주문 여부를 반환한다.",httpMethod = "GET")
    @GetMapping("/firstorder")
    public ResponseEntity<Boolean> getFirstcheckByCustomerNo(Integer customerno) {
        Customer customer = customerRepository.findOne(customerno);
        return new ResponseEntity(customer.isCustomerIndex(), HttpStatus.OK);
    }

}
