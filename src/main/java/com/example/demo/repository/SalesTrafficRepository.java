package com.example.demo.repository;

import com.example.demo.model.SalesTraffic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesTrafficRepository extends MongoRepository<SalesTraffic, String> {
}

