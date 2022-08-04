package com.jangbo.api.service;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.dto.StoreDto;
import com.jangbo.db.entity.Market;
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

    public List<StoreDto> findStoresByMarketNo(Integer marketno) {
        return marketRepository.findStoresByMarketNo(marketno);
    }

//    public Market insertMarket(String marketname, String marketaddr, Float lat, Float lng, String sidogugun) {
//        //MarketDto market = new MarketDto(marketNo,marketname, marketaddr, lat, lng, sidogugun);
//        return marketRepository.save(new Market(marketname, marketaddr, lat, lng));
//    }

}
