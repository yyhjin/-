package com.jangbo.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemNo;

    @NotBlank
    @Column(length=30)
    private String itemName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false,columnDefinition = "TINYINT",length=1)
    private boolean recent = true;

    @ManyToOne
    @JoinColumn(name="store_no")
    private Store store;


    @Builder
    public Item(String itemNo,Integer price,boolean recent,Store store){
        this.itemName =itemNo;
        this.price = price;
        this.recent = recent;
        this.store = store;
    }

    public void update(String itemNo,Integer price,boolean recent){
        this.itemName =itemNo;
        this.price = price;
        this.recent = recent;
    }
}
