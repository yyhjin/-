package com.jangbo.db.dto;


import lombok.Data;


@Data
public class SidoDto {
    private String sidoCode;
    private String sidoName;

    public SidoDto(String sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}