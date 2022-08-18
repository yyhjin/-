package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class  Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer customerNo;

    @NotBlank
    @Column(length = 20, unique = true)
    private String customerId;

    @NotBlank
    @Column(length = 15)
    private String customerName;

    @Column(length = 30)
    private String customerNickname;

    @Column(length = 150)
    private String customerAddr;

    @Column(length = 20)
    private String customerPhone;


    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean customerIndex = true; //첫 주문으로 기본 값 설정

    @NotBlank
    private String customerPwd;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt_id")
    private Salt salt;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<InterStore> interStores = new ArrayList<>();

    //==연관관계 편의 메서드==//
    public InterStore addInterStore(InterStore interStore, Integer storeNo) {
        interStore.setCustomer(this);
        interStore.setStoreNo(storeNo);
        interStores.add(interStore);
        return interStore;
    }

    public void addOrders(Orders order) {
        orders.add(order);
        order.setCustomer(this);
    }


}
