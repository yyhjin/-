package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Customer{
    @Id
    @Column(name = "customer_id", unique = true, length = 20)
    private String customerId;

    @NotBlank
    @Column(length = 15)
    private String customerName;

    @Column(length = 30)
    private String customerNickname;
    @Column(length = 150)
    private String customerAddr;
    @Column(length = 20)
    private String customerPhone;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean customerIndex = true; //첫 주문으로 기본 값 설정

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String customerPwd;
}
