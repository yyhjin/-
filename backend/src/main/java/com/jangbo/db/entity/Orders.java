package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNo;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; //안되면 이거부터 수정하기

    @NotBlank
    private Integer storeNo;

    @NotBlank
    @Column(length = 10)
    private String status;

    @NotBlank
    private Integer marketNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;





}
