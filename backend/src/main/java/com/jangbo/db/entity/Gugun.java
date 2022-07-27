package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * 구,군 모델 정의.
 */
@Entity
@Getter
@Setter
public class Gugun {
    @Id
    @Column(name = "sido_name")
    private String sidoName;

    @Id
    @NotBlank
    @Column(name = "gugun_name")
    private String gugunName;

    @NotBlank
    private String gugunCode;

    @OneToMany(mappedBy = "gugun")
    private List<Market> markets = new ArrayList<>();


//    @OneToMany(mappedBy = "gugun")
//    private List<Store> store = new ArrayList<>();
}
