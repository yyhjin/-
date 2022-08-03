package com.jangbo.db.dto;


import lombok.Data;

@Data
public class MarketDto {
    private Integer marketNo ;
    private String marketName;
    private String marketAddr;
    private Float lat;
    private Float lng;
    private String sidoGugun;

    public MarketDto(Integer marketNo,String marketName, String marketAddr, Float lat, Float lng, String sidoGugun) {
        this.marketNo = marketNo;
        this.marketName = marketName;
        this.marketAddr = marketAddr;
        this.lat = lat;
        this.lng = lng;
        this.sidoGugun = sidoGugun;
    }

    public MarketDto(Integer marketNo, String marketName, String marketAddr, Float lat, Float lng) {
        this.marketNo = marketNo;
        this.marketName = marketName;
        this.marketAddr = marketAddr;
        this.lat = lat;
        this.lng = lng;
    }
}