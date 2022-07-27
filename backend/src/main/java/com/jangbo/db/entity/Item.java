package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_no")
    private int ItemNo;

    @NotBlank
    private String item_name;

    @NotBlank
    private int price;

    @NotBlank
    private boolean recent;
}
