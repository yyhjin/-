package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Seller {
    @Id
    @Column(name = "business_number")
    private String businessNumber;

    @NotEmpty
    private String sellerId;
    @NotEmpty
    private String sellerName;
    @NotEmpty
    private String sellerPhone;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sellerPwd;
}
