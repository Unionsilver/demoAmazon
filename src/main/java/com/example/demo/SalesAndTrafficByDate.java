package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesAndTrafficByDate {
    private LocalDate date;
    private int totalSales;
    private int totalTraffic;

    // Конструктор без аргументов для использования в фреймворках
    public SalesAndTrafficByDate() {
    }

    // Конструктор с аргументами для удобного создания экземпляров
    public SalesAndTrafficByDate(LocalDate date, int totalSales, int totalTraffic) {
        this.date = date;
        this.totalSales = totalSales;
        this.totalTraffic = totalTraffic;
    }

    // Геттеры и сеттеры
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }

    public void setTotalTraffic(int totalTraffic) {
        this.totalTraffic = totalTraffic;
    }

    // Переопределенный метод toString для удобного представления объекта в виде строки
    @Override
    public String toString() {
        return "SalesAndTrafficByDate{" +
                "date=" + date +
                ", totalSales=" + totalSales +
                ", totalTraffic=" + totalTraffic +
                '}';
    }
}
