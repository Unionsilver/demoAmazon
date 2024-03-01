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

@Document(collection = "salesTraffic")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class SalesTraffic {
    @Id
    private String id;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;

    public SalesTraffic(String id, List<SalesAndTrafficByDate> salesAndTrafficByDate, List<SalesAndTrafficByAsin> salesAndTrafficByAsin) {
        this.id = id;
        this.salesAndTrafficByDate = salesAndTrafficByDate;
        this.salesAndTrafficByAsin = salesAndTrafficByAsin;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SalesTraffic.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("salesAndTrafficByDate=" + salesAndTrafficByDate)
                .add("salesAndTrafficByAsin=" + salesAndTrafficByAsin)
                .toString();
    }
}
