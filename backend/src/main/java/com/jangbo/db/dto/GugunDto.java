package com.jangbo.db.dto;


import lombok.Data;

@Data
public class GugunDto {
    private String sidoGugun ;
    private String gugunCode;
    private String gugunName;

    public GugunDto(String sidoGugun, String gugunCode, String gugunName) {
        this.sidoGugun = sidoGugun;
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
    }
}