package com.jangbo.api.service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jangbo.api.request.StoreRegisterPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FileService {
    //private final PostRepository postRepository;

    private AmazonS3 amazonS3;


    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @PostConstruct
    public void amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3= AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }


    public String fileUpload(StoreRegisterPostReq store, MultipartFile multipartFile) throws IOException{
        //s3 관련
        String fileName = createFileName(store.getStoreName(),store.getSellerNo(),multipartFile.getOriginalFilename());
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(multipartFile.getContentType());
        System.out.println(bucket);
        System.out.println(fileName);
        System.out.println(multipartFile.getInputStream());
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        String imgUrl = amazonS3.getUrl(bucket, fileName).toString();
        System.out.println(imgUrl);
        return imgUrl;
    }

    ////////////////////////------------S3관련---------------//////////////////////////////


    public void deleteImage(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String storeName,int sellerNo,String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmSS");
        String changedName = storeName+"-"+sellerNo+"-"+fileName;
        System.out.println(changedName);
        getFileExtension(fileName);
        return storeName.concat(getFileExtension(fileName));
    }


    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}