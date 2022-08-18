package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date orderDate;

    private Integer marketNo;

    private Integer storeNo;

    @NotBlank
    @Column(length = 10)
    private String status;

    @ManyToOne(fetch = LAZY ,cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_no")
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();


}
