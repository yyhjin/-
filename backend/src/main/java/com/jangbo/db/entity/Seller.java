package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Seller {
    @Id
    @NotEmpty
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
