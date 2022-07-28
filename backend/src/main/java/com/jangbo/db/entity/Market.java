package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.*;

/**
 * 시장 모델 정의.
 */
@Entity
@Getter
@Setter
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

    @NotBlank
    private Float lat;

    @NotBlank
    private Float lng;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "sido_name"),
//            @JoinColumn(name = "gugun_name")
//    })
    //private Gugun gugun;

}
