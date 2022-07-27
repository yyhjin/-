package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Customer{
    @Id
    @Column(name = "customer_id")
    private String customerId;

    @NotBlank
    private String customerName;

    private String customerNickname;
    private String customerAddr;
    private String customerPhone;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean customerIndex = true; //첫 주문으로 기본 값 설정

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String customerPwd;
}
