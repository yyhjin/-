package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * 대화방 호출 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CallOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer callOrderNo;

    @Column(nullable = false)
    private Integer orderNo;

    @Column(length = 20, nullable = false)
    private String customerId;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "store_no")
//    private Store store;
    @Column(nullable = false)
    private Integer storeNo;

    public CallOrder(Integer orderNo, String customerId, Integer storeNo) {
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.storeNo = storeNo;
    }


}
