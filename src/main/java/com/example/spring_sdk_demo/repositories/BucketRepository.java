package com.example.spring_sdk_demo.repositories;

import com.example.spring_sdk_demo.models.Bucket;
import org.springframework.data.repository.CrudRepository;

public interface BucketRepository extends CrudRepository<Bucket, Long> {
}
