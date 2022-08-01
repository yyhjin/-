package com.jangbo.api.controller;

import com.jangbo.api.service.StoreService;
import com.jangbo.db.entity.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Api(value = "상점api", tags={"상점"})
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;
    @GetMapping("/{storeNo}")
    @ApiOperation(value = "상점api" , notes="설명",httpMethod = "GET")
    public ResponseEntity<Store> getStore(@PathVariable int storeNo){
        Optional<Store> store = storeService.selectBystoreNo(storeNo);
        return new ResponseEntity<Store>(store.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{storeNo}")
    @ApiOperation(value = "상점api", notes="상점번호로 정보삭제",httpMethod = "DELETE")
    public ResponseEntity<Void> deleteStore(@PathVariable int storeNo) {
        storeService.deleteBystoreNo(storeNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
