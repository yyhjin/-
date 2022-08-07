package com.jangbo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileNo;
    @Column(length=300,nullable = false)
    private String path;

    @Column(nullable = false)
    private Integer storeNo;


    @Builder
    public File(Integer fileNo,String path,Integer storeNo){
        this.fileNo =fileNo;
        this.path = path;
        this.storeNo = storeNo;

    }

    public void update(String path){
        this.path =path;


    }

}
