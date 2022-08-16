package com.jangbo.api.controller;

import com.jangbo.api.service.CallOrderService;
import com.jangbo.api.service.StoreService;
import com.jangbo.db.dto.CallOrderDto;
import com.jangbo.db.entity.CallOrder;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Api(value = "호출 api", tags={"호출"})
@RestController
@RequestMapping("/be/call")
public class CallOrderController {

    @Autowired
    CallOrderService callOrderService;
    StoreService storeService;
    StoreRepository storeRepository;

    @ApiOperation(value = "상점번호로 호출 조회" , notes="상점번호로 호출 내역 정보를 조회한다.",httpMethod = "GET")
    @GetMapping
    public ResponseEntity<CallOrderDto> findCallByStoreNo(Integer storeno) {
        List<CallOrderDto> calls = callOrderService.findAllByStoreNoOrderByOrderNoAsc(storeno);
        return new ResponseEntity(calls, HttpStatus.OK);
    }

    @ApiOperation(value = "호출 생성" , notes="상점 번호, 소비자 아이디로 호출을 생성한다.",httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Integer> createCall(Integer storeNo, String customerId) {

        // 전체 컬럼 개수 조회
        Integer count = callOrderService.getCount(storeNo);

//        Store store = storeService.findStoreByStoreNo(storeNo);
//
        CallOrder createcall = new CallOrder(count+1, customerId, storeNo);

//        createcall.setCallOrderNo(count+1);
//        createcall.setCustomerId(customerId);
//        createcall.setStore(store);

        Integer callno = callOrderService.callsave(createcall).getOrderNo();

        return new ResponseEntity(callno, HttpStatus.OK);
    }


    @ApiOperation(value = "상점번호, 소비자아이디로 호출 삭제" , notes="소비자아이디로 해당 상점의 호출 정보를 삭제한다.",httpMethod = "DELETE")
    @DeleteMapping
    @Transactional
    public ResponseEntity<Integer> deleteCall(Integer storeNo, String customerId) {

        // 지워질 호출 데이터
        CallOrder target = callOrderService.findByStoreNoAndCustomerId(storeNo, customerId);
        int index = target.getOrderNo();
        int count = callOrderService.getCount(storeNo);

        // 삭제
        callOrderService.delete(target);

        // 주문순서 1번이 지워질 때 - 전체 orderno 업데이트
        if(index == 1) {
            callOrderService.updateAll(storeNo);
        }
        // 주문순서 중간 번호가 지워질 때 - 뒤 호출의 orderno 업데이트
        else if(1 < index  && index < count) {
            callOrderService.updateByIndex(storeNo, index);
        }


        return new ResponseEntity(storeNo, HttpStatus.NO_CONTENT);
    }


}
