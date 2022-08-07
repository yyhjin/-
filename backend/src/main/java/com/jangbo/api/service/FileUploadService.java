package com.jangbo.api.service;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jangbo.db.entity.File;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.StoreRepository;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@NoArgsConstructor
public class FileUploadService {
   private AmazonS3 s3Client;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    private  StoreRepository storeRepository;

public void setS3Client(){
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey,this.secretKey);
    s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(this.region)
            .build();
}
    public String fileUpload(MultipartFile multipartFile) throws IOException{
         String fileName = multipartFile.getOriginalFilename();
         s3Client.putObject(new PutObjectRequest(bucket,fileName, multipartFile.getInputStream(),null)
                 .withCannedAcl(CannedAccessControlList.PublicRead));
         return s3Client.getUrl(bucket,fileName).toString();

//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentLength(multipartFile.getSize());
//        objectMetadata.setContentType(multipartFile.getContentType());
//
//        try(InputStream inputStream = multipartFile.getInputStream()) {
//            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
//
//        }catch (IOException e){
//            throw new ResponseStatusException((HttpStatus.INTERNAL_SERVER_ERROR,"파일업로드에 실패했습니다.");
//        }
//        //원본파일 이름과 변경된 파일 이름을 DB에 저장


    }


}
