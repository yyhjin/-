package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemNo;

    private Integer count;

    private Integer price;

    private String itemName;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_no")
    private Orders orders;



}
