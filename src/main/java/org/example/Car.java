package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Car {
    @JsonProperty("brand")
    @JacksonXmlProperty(localName = "brand")
    private String brand;

    @JsonProperty("model")
    @JacksonXmlProperty(localName = "model")
    private String model;

    @JsonProperty("year")
    @JacksonXmlProperty(localName = "year")
    private int year;

    @JsonProperty("color")
    @JacksonXmlProperty(localName = "color")
    private String color;

    public Car() {
    }

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}
