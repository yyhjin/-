package com.jangbo.api.controller;

import com.jangbo.api.service.SidoService;
import com.jangbo.db.dto.SidoDto;
import com.jangbo.db.entity.Gugun;
import com.jangbo.db.entity.Sido;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "시,도api", tags={"시,도"})
@RestController
@RequestMapping("sido")
public class SidoController {

    @Autowired
    SidoService sidoService;

    @ApiOperation(value = "시,도 목록 조회" , notes="시,도 전체 목록을 조회한다.",httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<Sido>> getAllSidos() {
        List<SidoDto> sidos = sidoService.findAllSubstring();
        return new ResponseEntity(sidos, HttpStatus.OK);
    }
}
