package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Store store;


    @Builder
    public Item(Integer itemNo,String itemName,Integer price,boolean recent,Store store){
        this.itemNo = itemNo;
        this.itemName =itemName;
        this.price = price;
        this.recent = recent;
        this.store = store;
    }

    public void update(String itemName,Integer price){
        this.itemName =itemName;
        this.price = price;

    }
    public void isRecent(boolean recent){
        this.recent = recent;
    }

}
