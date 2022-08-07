//package com.jangbo.db.dto;
//
//import com.jangbo.db.entity.File;
//import com.jangbo.db.entity.Store;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@Data
//public class FileDto {
//    private Integer fileNo;
//    private String path;
//    private Integer storeNo;
//
//    public File toEntity() {
//        return File.builder()
//                .fileNo(this.fileNo)
//                .path(this.path)
//                .storeNo(this.storeNo)
//                .build();
//    }
//
//    @Builder
//    public FileDto(Integer fileNo, String path,Integer storeNo) {
//        this.fileNo = fileNo;
//        this.path = path;
//        this.storeNo = storeNo;
//    }
//}
