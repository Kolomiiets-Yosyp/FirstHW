package com.profitsoft.service;

import com.profitsoft.entity.Car;
import com.profitsoft.utils.CalculationStatistics;
import com.profitsoft.utils.UtilService;
import com.profitsoft.utils.XmlFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CarService {
    private final XmlFactory xmlFactory = new XmlFactory();
    private final UtilService utilService = new UtilService();
    private final CalculationStatistics calcStatistics = new CalculationStatistics();

    public void getCountOfAttribute(String directoryPath, String attribute, int numberOfThreads) {
        try {
            Field[] fields = Car.class.getDeclaredFields();
            List<String> attributes = Arrays.stream(fields)
                    .map(Field::getName)
                    .collect(Collectors.toList());
            if (!attribute.isEmpty() && !attributes.contains(attribute)) {
                System.out.println("Wrong attribute name");
                return;
            }

            // Отримуємо список JSON-файлів у папці
            List<String> jsonFilesList = utilService.getJsonFilesList(directoryPath);
            if (jsonFilesList.isEmpty()) {
                System.out.println("Directory doesn't have any JSON files");
                return;
            }

            // Парсимо файли в паралельних потоках
            List<Car> cars = utilService.parseAll(jsonFilesList, Car.class, numberOfThreads);
            if (cars.isEmpty()) {
                System.out.println("No cars found in JSON files");
                return;
            }

            // Генеруємо XML-файл зі статистикою
            generateStatisticsXml(directoryPath, attribute, cars);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void generateStatisticsXml(String directoryPath, String attribute, List<Car> cars) {
        try {
            HashMap<String, HashMap<String, Integer>> toXml = new HashMap<>();
            if (attribute.isEmpty()) {
                Field[] fields = Car.class.getDeclaredFields();
                for (Field field : fields) {
                    if (!field.getName().equals("carTags")) {
                        toXml.put(field.getName(), calcStatistics.getAttributeCount(cars, field.getName()));
                    }
                }
                toXml.put("carTags", calcStatistics.getTagAttributeCount(cars));
            } else {
                if (attribute.equals("carTags")) {
                    toXml.put(attribute, calcStatistics.getTagAttributeCount(cars));
                } else {
                    toXml.put(attribute, calcStatistics.getAttributeCount(cars, attribute));
                }
            }

            xmlFactory.toXml(directoryPath, toXml);
        } catch (Exception e) {
            System.out.println("Error generating XML: " + e.getMessage());
        }
    }
}
