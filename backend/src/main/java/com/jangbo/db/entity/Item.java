package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ItemNo;

    @NotBlank
    @Column(length=30)
    private String item_name;

    @NotBlank
    private Integer price;

    @Column(nullable = false,columnDefinition = "TINYINT",length=1)
    private boolean recent = true;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="store_no")
    private Store store;


}
