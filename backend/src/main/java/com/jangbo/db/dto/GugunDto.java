package com.jangbo.db.dto;


import lombok.Data;

@Data
public class GugunDto {
    private String gugunCode ;
    private String sidoName;
    private String gugunName;

    public GugunDto(String gugunCode, String sidoName, String gugunName) {
        this.gugunCode = gugunCode;
        this.sidoName = sidoName;
        this.gugunName = gugunName;
    }
}