package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jangbo.api.response.StoreInfoRes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * 시장 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer marketNo;

    @Column(length = 75)
    @NotBlank
    private String marketName;

    @Column(length = 150)
    @NotBlank
    private String marketAddr;

    private Float lat;

    private Float lng;

    @ManyToOne
    @JoinColumn(name = "sido_gugun")
    @JsonIgnore
    private Gugun gugun;


    public Market(String marketName, String marketAddr, Float lat, Float lng) {
        this.marketName = marketName;
        this.marketAddr = marketAddr;
        this.lat = lat;
        this.lng = lng;

    }


}
