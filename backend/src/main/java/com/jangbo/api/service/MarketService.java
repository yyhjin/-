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

    public List<MarketDto> findAllByGugun_SidoGugunStartsWith(String sidogugun) {
        return marketRepository.findAllByGugun_SidoGugunStartsWith(sidogugun);
    }

    public List<MarketDto> findAllByMarketNameContains(String marketname) {
        return marketRepository.findAllByMarketNameContains(marketname);
    }

    public MarketDto findMarketByMarketNo(Integer marketno) {
        return marketRepository.findMarketByMarketNo(marketno);
    }


}
