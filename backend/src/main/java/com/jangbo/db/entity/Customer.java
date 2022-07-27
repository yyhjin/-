package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Customer{
    @Id @NotEmpty
    private String customerId;

    @NotEmpty
    private String customerName;

    private String customerNickname;
    private String customerAddr;
    private String customerPhone;
    private int customerIndex = 1;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String customerPwd;
}
