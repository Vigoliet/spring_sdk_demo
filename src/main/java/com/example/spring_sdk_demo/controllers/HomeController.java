package com.example.spring_sdk_demo.controllers;

import com.example.spring_sdk_demo.models.Bucket;
import com.example.spring_sdk_demo.repositories.BucketRepository;
import com.example.spring_sdk_demo.s3bucket.S3Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private S3Example s3Example;

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @PostMapping("/createBucketPost")
    public String createBucket(@RequestParam("bucketName") String bucketName) {
        s3Example.createBucket(bucketName);
        return "redirect:/";
    }

    @GetMapping("/listBuckets")
    public String listBuckets(Model model) {
        List<Bucket> buckets = s3Example.listBuckets();
        model.addAttribute("buckets", buckets);
        return "bucketList";
    }

}
