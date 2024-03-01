package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDate;

// Аннотация Lombok для автоматической генерации геттеров, сеттеров, а также методов toString(), equals() и hashCode()
@Data
// Аннотация Spring Data MongoDB для указания, что данный класс является документом MongoDB и будет храниться в коллекции "sale_statistics"
@Document(collection = "sale_statistics")
public class SaleStatistics {
    @Id // Указывает, что поле является идентификатором документа в MongoDB
    private String id; // Уникальный идентификатор записи статистики продаж
    private String asin; // ASIN (Amazon Standard Identification Number) товара
    private int salesQuantity; // Количество проданных единиц товара
    private LocalDate saleDate; // Дата продажи

    // Конструктор без аргументов, необходимый для корректной работы Lombok и фреймворков, использующих рефлексию
    public SaleStatistics() {
    }

    // Конструктор для инициализации всех полей класса, позволяет создать экземпляр с предварительно заданными значениями
    public SaleStatistics(String asin, int salesQuantity, LocalDate saleDate) {
        this.asin = asin; // Установка ASIN товара
        this.salesQuantity = salesQuantity; // Установка количества проданных единиц
        this.saleDate = saleDate; // Установка даты продажи
    }

    // Геттеры и сеттеры для всех полей будут сгенерированы автоматически благодаря аннотации @Data
}
