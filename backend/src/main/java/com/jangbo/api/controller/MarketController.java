package com.jangbo.api.controller;

import com.jangbo.api.service.MarketService;
import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.dto.StoreDto;
import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "시장 api", tags={"시장"})
@RestController
@RequestMapping("market")
public class MarketController {

    @Autowired
    MarketService marketService;

    @ApiOperation(value = "주소로 시장 목록 조회" , notes="시,도 이름과 구,군 이름으로 시장 목록을 조회한다.",httpMethod = "GET")
    @GetMapping("/addr")
    public ResponseEntity<List<MarketDto>> getAllBySidogugun(String sidogugun) {
        List<MarketDto> markets = marketService.findAllByGugun_SidoGugunStartsWith(sidogugun);
        return new ResponseEntity(markets, HttpStatus.OK);
    }

    @ApiOperation(value = "시장이름으로 시장 목록 조회" , notes="시장이름으로 시장 목록을 조회한다.",httpMethod = "GET")
    @GetMapping("/name")
    public ResponseEntity<List<MarketDto>> getAllByMarketname(String marketname) {
        List<MarketDto> markets = marketService.findAllByMarketNameContains(marketname);
        return new ResponseEntity(markets, HttpStatus.OK);
    }


//    @PostMapping("/create")
//    public ResponseEntity<List<Market>> createMarket(String marketname, String marketaddr, Float lat, Float lng, String sidogugun) {
//        Market market = marketService.insertMarket(marketname, marketaddr, lat, lng, sidogugun);
//        return new ResponseEntity(market, HttpStatus.OK);
//    }
}
