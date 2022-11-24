package com.jangbo.api.service;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    @Autowired
    MarketRepository marketRepository;

    /** 지역 이름으로 시장 목록 조회 */
    public List<MarketDto> findAllByGugun_SidoGugunStartsWith(String sidogugun) {
        return marketRepository.findAllByGugun_SidoGugunStartsWith(sidogugun);
    }

    /** 시장이름으로 시장 목록 조회 */
    public List<MarketDto> findAllByMarketNameContains(String marketname) {
        return marketRepository.findAllByMarketNameContains(marketname);
    }

    /** 시장번호로 시장 조회 */
    public MarketDto findMarketByMarketNo(Integer marketno) {
        return marketRepository.findMarketByMarketNo(marketno);
    }


}
