package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDate;

@Document(collection = "sale_statistics")
@Data // Генерирует геттеры, сеттеры, toString(), equals() и hashCode() методы
public class SaleStatistics {
    @Id
    private String id;
    private String asin;
    private int salesQuantity;
    private LocalDate saleDate;

    // Конструктор без аргументов для Lombok
    public SaleStatistics() {
    }

    // Конструктор для инициализации всех полей
    public SaleStatistics(String asin, int salesQuantity, LocalDate saleDate) {
        this.asin = asin;
        this.salesQuantity = salesQuantity;
        this.saleDate = saleDate;
    }

    // Остальные геттеры и сеттеры будут сгенерированы автоматически
}
