package com.jangbo.api.controller;

import com.jangbo.api.service.StoreService;
import com.jangbo.db.entity.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Store> getStore(@PathVariable int storeNo){
        return storeService.selectBystoreNo(storeNo);
    }

}
