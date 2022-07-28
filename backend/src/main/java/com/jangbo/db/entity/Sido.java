package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 시,도 모델 정의.
 */
@Entity
@Getter
@Setter
public class Sido{
    @Id
    @Column(length = 20)
    private String sidoCode;

    @Column(length = 75)
    @NotBlank
    private String sidoName;
}
