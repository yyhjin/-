package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_no")
    private int orderItemNo;

    private int count;
    private int orderPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_no")
    private Order order;



}
