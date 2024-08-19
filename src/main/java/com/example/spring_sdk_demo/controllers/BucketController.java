package com.example.spring_sdk_demo.controllers;

import com.example.spring_sdk_demo.models.Bucket;
import com.example.spring_sdk_demo.repositories.BucketRepository;
import com.example.spring_sdk_demo.s3bucket.S3Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BucketController {


    @Autowired
    private BucketRepository bucketRepository;
    private S3Example s3Example;


    @GetMapping("/buckets")
    public List<Bucket> getBuckets() {
        S3Example s3Example = new S3Example();
        var buckets = s3Example.listBuckets();
        System.out.println(buckets);
        return buckets;
    }

    // Right now i create the bucket in the request, however this will be a field in the front end
    @GetMapping("/createBucket{bucketName}")
    public void createBucket(@PathVariable String bucketName) {
        S3Example s3Example = new S3Example();
        s3Example.createBucket(bucketName);
    }








}
