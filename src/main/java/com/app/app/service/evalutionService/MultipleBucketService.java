package com.app.app.service.evalutionService;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MultipleBucketService {

    private final S3Client s3Client;

    public MultipleBucketService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> uploadFiles(MultipartFile[] files, String bucketName, String folderName) {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = folderName + "/" + file.getOriginalFilename();
            try {
                PutObjectResponse response = s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(fileName)
                                .contentType(file.getContentType())
                                .build(),
                        RequestBody.fromBytes(file.getBytes()));

                String fileUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
                fileUrls.add(fileUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
            }
        }

        return fileUrls;
    }
}
