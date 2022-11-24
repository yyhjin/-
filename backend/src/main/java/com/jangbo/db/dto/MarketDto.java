package com.jangbo.db.dto;


import com.jangbo.db.entity.Market;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MarketDto {
    private Integer marketNo ;
    private String marketName;
    private String marketAddr;
    private Float lat;
    private Float lng;


    public MarketDto(Market market) {
        this.marketNo = market.getMarketNo();
        this.marketName = market.getMarketName();
        this.marketAddr = market.getMarketAddr();
        this.lat = market.getLat();
        this.lng = market.getLng();
    }
}