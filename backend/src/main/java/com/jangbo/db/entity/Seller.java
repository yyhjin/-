package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue
    private Integer sellerNo;

    @Column(name = "business_number",length = 15, unique = true)
    private String businessNumber;

    @NotBlank
    @Column(length = 20, unique = true)
    private String sellerId;
    @NotBlank
    @Column(length = 15)
    private String sellerName;
    @NotBlank
    @Column(length = 20)
    private String sellerPhone;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sellerPwd;
}
