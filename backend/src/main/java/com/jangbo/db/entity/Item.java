package com.jangbo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
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

    @ManyToOne
    @JoinColumn(name="store_no")
    private Store store;

}
