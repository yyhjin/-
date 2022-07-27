package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * 시,도 모델 정의.
 */
@Entity
@Getter
@Setter
public class Sido{
    @Id
    @NotEmpty
    private String sido_code;

    @NotEmpty
    private String sido_name;
}
