package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "sale_statistics")
public class SaleStatistics {
    @Id
    private String id;
    private String asin;
    private int salesQuantity;
    private Date saleDate;

    // Конструктор и геттеры и сеттеры
    public SaleStatistics() {
    }

    public SaleStatistics(String asin, int salesQuantity, Date saleDate) {
        this.asin = asin;
        this.salesQuantity = salesQuantity;
        this.saleDate = saleDate;
    }

    // Геттеры та сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
