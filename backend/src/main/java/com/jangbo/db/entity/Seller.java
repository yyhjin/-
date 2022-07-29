package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue
    private Integer sellerNo;

    @NotBlank
    @Column(length = 20, unique = true)
    private String sellerId;

    //API로 값이 제대로 보내지는지, 받아지는지 확인하고 수정할 것
//    @NotBlank
    @Column(name = "business_number",length = 15, unique = true)
    private String businessNumber;

//    @NotBlank
    @Column(length = 15)
    private String sellerName;

//    @NotBlank
    @Column(length = 20)
    private String sellerPhone;

//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "store_no")
//    private Store store;

//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String sellerPwd;
}
