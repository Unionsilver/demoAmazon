package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "salesTraffic")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesTraffic {
    @Id
    private String id;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;

    // Геттери та сеттери


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SalesAndTrafficByDate> getSalesAndTrafficByDate() {
        return salesAndTrafficByDate;
    }

    public void setSalesAndTrafficByDate(List<SalesAndTrafficByDate> salesAndTrafficByDate) {
        this.salesAndTrafficByDate = salesAndTrafficByDate;
    }

    public List<SalesAndTrafficByAsin> getSalesAndTrafficByAsin() {
        return salesAndTrafficByAsin;
    }

    public void setSalesAndTrafficByAsin(List<SalesAndTrafficByAsin> salesAndTrafficByAsin) {
        this.salesAndTrafficByAsin = salesAndTrafficByAsin;
    }
}

