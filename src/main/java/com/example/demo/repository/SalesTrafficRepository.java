package com.example.demo.repository;

import com.example.demo.SalesTraffic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesTrafficRepository extends MongoRepository<SalesTraffic, String> {
}

