package com.profitsoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Car {
    private String brand;
    private String model;
    private int year;

}
