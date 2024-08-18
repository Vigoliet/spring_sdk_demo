package com.example.spring_sdk_demo.s3bucket;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class S3Example {

    private final S3Client s3;
    private String bucketName;

    public S3Example() {
        s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void createBucket(String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();

        CreateBucketResponse response = s3.createBucket(createBucketRequest);
        System.out.println(response.location());

        this.bucketName = bucketName;
    }

    public void uploadFile(String keyName, String filePath) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        Path path = Paths.get(filePath);
        PutObjectResponse response = s3.putObject(request, path);
        System.out.println(response.toString());
    }

    public void readFile(String keyName) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        String response = s3.getObjectAsBytes(request).asUtf8String();
        System.out.println(response);
    }

    public void deleteObject(String keyName) {
        System.out.println("Deleting object from bucket: " + bucketName);
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        DeleteObjectResponse response = s3.deleteObject(request);
        System.out.println(response.toString());
    }

    public void deleteBucket() {
        System.out.println("Deleting bucket: " + bucketName);
        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3.deleteBucket(deleteBucketRequest);
        System.out.println("Bucket deleted: " + bucketName);
    }

    public void listBuckets() {
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().forEach(x -> System.out.println(x.name()));
    }
}