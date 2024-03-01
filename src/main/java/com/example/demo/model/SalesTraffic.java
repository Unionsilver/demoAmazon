package com.example.demo.model;

import com.example.demo.SalesAndTrafficByAsin;
import com.example.demo.SalesAndTrafficByDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.StringJoiner;

// Аннотация для указания, что класс является документом MongoDB и будет храниться в коллекции "salesTraffic"
@Document(collection = "salesTraffic")
// Аннотация для игнорирования неизвестных свойств при десериализации JSON, что полезно при работе с внешними API
@JsonIgnoreProperties(ignoreUnknown = true)
@Data // Генерация геттеров, сеттеров, методов toString(), equals() и hashCode() с помощью библиотеки Lombok
@NoArgsConstructor // Генерация конструктора без аргументов
public class SalesTraffic {
    @Id // Указывает, что поле является идентификатором документа в MongoDB
    private String id; // Уникальный идентификатор записи о трафике продаж
    private List<SalesAndTrafficByDate> salesAndTrafficByDate; // Список данных о продажах и трафике по датам
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin; // Список данных о продажах и трафике по ASIN

    // Конструктор с параметрами для инициализации объекта
    public SalesTraffic(String id, List<SalesAndTrafficByDate> salesAndTrafficByDate, List<SalesAndTrafficByAsin> salesAndTrafficByAsin) {
        this.id = id; // Установка идентификатора
        this.salesAndTrafficByDate = salesAndTrafficByDate; // Установка списка данных по датам
        this.salesAndTrafficByAsin = salesAndTrafficByAsin; // Установка списка данных по ASIN
    }

    // Переопределенный метод toString() для представления объекта в строковом формате
    @Override
    public String toString() {
        return new StringJoiner(", ", SalesTraffic.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("salesAndTrafficByDate=" + salesAndTrafficByDate)
                .add("salesAndTrafficByAsin=" + salesAndTrafficByAsin)
                .toString();
    }
}
