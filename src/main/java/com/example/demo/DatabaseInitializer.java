package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Arrays;

@Component
public class DatabaseInitializer {

    @Autowired
    private SaleStatisticsRepository repository;

    @PostConstruct
    public void initData() {
        // Завантажуємо дані з JSON файлу
        ObjectMapper mapper = new ObjectMapper();
        try {
            SaleStatistics[] statistics = mapper.readValue(new File("test_report.json"), SaleStatistics[].class);
            repository.saveAll(Arrays.asList(statistics));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
