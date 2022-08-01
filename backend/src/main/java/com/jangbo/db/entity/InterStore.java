package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class InterStore {

    @Id
    @GeneratedValue
    @Column(name = "cus_store_no")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "store_no")
//    private Store store;

}
