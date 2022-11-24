package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerNo;

    @NotBlank
    @Column(length = 20, unique = true)
    private String sellerId;

    @NotBlank
    @Column(name = "business_number",length = 15, unique = true)
    private String businessNumber;

    @NotBlank
    @Column(length = 15)
    private String sellerName;

    @NotBlank
    @Column(length = 20)
    private String sellerPhone;

    @NotBlank
    private String sellerPwd;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt_id")
    private Salt salt;

}
