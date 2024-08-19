package com.example.spring_sdk_demo.controllers;

import com.example.spring_sdk_demo.models.Bucket;
import com.example.spring_sdk_demo.repositories.BucketRepository;
import com.example.spring_sdk_demo.s3bucket.S3Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BucketController {


    @Autowired
    private BucketRepository bucketRepository;

    @RequestMapping("/buckets")
    public List<Bucket> getBuckets() {
        S3Example s3Example = new S3Example();
        var buckets = s3Example.listBuckets();
        System.out.println(buckets);
        return buckets;
    }



}
