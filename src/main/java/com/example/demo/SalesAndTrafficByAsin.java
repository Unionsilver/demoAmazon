package com.example.demo;

import java.util.Objects;

public class SalesAndTrafficByAsin {

    private String asin;
    private int totalSales;
    private int totalTraffic;

    // Конструктор без аргументов
    public SalesAndTrafficByAsin() {
    }

    // Конструктор с аргументами
    public SalesAndTrafficByAsin(String asin, int totalSales, int totalTraffic) {
        this.asin = asin;
        this.totalSales = totalSales;
        this.totalTraffic = totalTraffic;
    }

    // Геттеры и сеттеры
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
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

    // Переопределенные методы equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesAndTrafficByAsin)) return false;
        SalesAndTrafficByAsin that = (SalesAndTrafficByAsin) o;
        return totalSales == that.totalSales &&
                totalTraffic == that.totalTraffic &&
                Objects.equals(asin, that.asin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, totalSales, totalTraffic);
    }

    // Переопределенный метод toString
    @Override
    public String toString() {
        return "SalesAndTrafficByAsin{" +
                "asin='" + asin + '\'' +
                ", totalSales=" + totalSales +
                ", totalTraffic=" + totalTraffic +
                '}';
    }
}
