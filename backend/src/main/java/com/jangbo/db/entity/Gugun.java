package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
    @Column(length = 150)
    private String sidoGugun;

    @Column(length = 75)
    @NotBlank
    private String gugunName;

    @Column(length = 20)
    @NotBlank
    private String gugunCode;

}
