package com.jangbo.api.controller;

import com.jangbo.api.service.GugunService;
import com.jangbo.db.dto.GugunDto;
import com.jangbo.db.entity.Gugun;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "구,군 api", tags={"구,군"})
@RestController
@RequestMapping("/be/gugun")
public class GugunController {

    @Autowired
    GugunService gugunService;

    @ApiOperation(value = "구,군 목록 조회" , notes="구,군 전체 목록을 조회한다.",httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<Gugun>> getAllBySido(String sidocode) {
        List<GugunDto> guguns = gugunService.findAllByGugunCodeStartsWith(sidocode);
        return new ResponseEntity(guguns, HttpStatus.OK);
    }
}
