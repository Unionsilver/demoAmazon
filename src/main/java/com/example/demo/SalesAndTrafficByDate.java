package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesAndTrafficByDate {
    private LocalDate date;
    // інші поля

}