package com.jangbo.api.controller;

import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.api.service.*;
import com.jangbo.db.dto.OrderItemDto;
import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "주문 api", tags={"주문"})
@RestController
@RequestMapping("order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CustomerService customerService;
    @Autowired
    StoreService storeService;
    @Autowired
    ItemService itemService;

    @ApiOperation(value = "주문목록 조회(소비자)" , notes="소비자번호로 해당 소비자의 주문목록을 조회한다.",httpMethod = "GET")
    @GetMapping("/customer")
    public ResponseEntity<List<OrdersDto>> getAllByCustomerNo(Integer customerno) {
        List<OrdersDto> orders = ordersService.findAllByCustomer_CustomerNo(customerno);
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "주문목록 조회(판매자)" , notes="상점번호로 해당 상점의 주문목록을 조회한다.",httpMethod = "GET")
    @GetMapping("/store")
    public ResponseEntity<List<OrdersDto>> getAllByStoreNo(Integer storeno) {
        List<OrdersDto> orders = ordersService.findAllByStoreNo(storeno);
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "첫주문여부 조회" , notes="소비자번호로 해당 소비자의 첫 주문 여부를 반환한다.",httpMethod = "GET")
    @GetMapping("/firstorder")
    public ResponseEntity<Boolean> getFirstcheckByCustomerNo(Integer customerno) {
        Customer customer = customerService.findOne(customerno);
        return new ResponseEntity(customer.isCustomerIndex(), HttpStatus.OK);
    }

    @ApiOperation(value = "주문상태 수정" , notes="주문번호에 해당하는 주문의 상태를 변경한다.",httpMethod = "PUT")
    @PutMapping
    @Transactional
    public ResponseEntity<OrdersDto> getFirstcheckByCustomerNo(Integer orderno, String status) {
        Orders order = ordersService.findOrdersByOrderNo(orderno);

        Orders updateorder = new Orders();
        updateorder.setCustomer(order.getCustomer());
        updateorder.setOrderNo(orderno);
        updateorder.setOrderDate(order.getOrderDate());
        updateorder.setOrderItems(order.getOrderItems());
        updateorder.setMarketNo(order.getMarketNo());
        updateorder.setStoreNo(order.getStoreNo());
        updateorder.setStatus(status);

        ordersService.updateOrderState(updateorder, orderno, status);

        Orders update = ordersService.findOrdersByOrderNo(orderno);
        OrdersDto result = new OrdersDto(update);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @ApiOperation(value = "주문" , notes="주문서를 생성한다.",httpMethod = "POST")
    @PostMapping
    @Transactional
    public ResponseEntity<Integer> createOrders(Integer storeno, Integer customerno, Integer itemno, Integer count) {

        // 주문서 생성
        Integer marketno = storeService.findStoreById(storeno).getMarket().getMarketNo();
        Customer customer = customerService.findOne(customerno);

        Orders createorder = new Orders();
        createorder.setCustomer(customer);
        //createorder.setOrderItems(order.getOrderItems());
        createorder.setMarketNo(marketno);
        createorder.setStoreNo(storeno);
        createorder.setStatus("ORDER");

        Integer orderno = ordersService.ordersave(createorder).getOrderNo();

        // 주문 아이템 생성
        Item item = itemService.findItemByItemNo(itemno);

        OrderItem orderitem = new OrderItem();
        orderitem.setOrders(createorder);
        orderitem.setCount(count);
        orderitem.setItemName(item.getItemName());
        orderitem.setPrice(item.getPrice()*count);



        Integer orderitemno = orderItemService.orderitemsave(orderitem).getOrderItemNo();
//        Orders update = ordersService.findOrdersByOrderNo(orderno);
//        OrdersDto result = new OrdersDto(update);
        return new ResponseEntity(orderitemno, HttpStatus.OK);
    }




}
