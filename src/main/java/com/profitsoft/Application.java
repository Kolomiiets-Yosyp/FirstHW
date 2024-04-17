package com.profitsoft;


import com.profitsoft.service.CarService;

public class Application {

    public static void main(String[] args) {
        String path = "/Users/imac/Desktop/FirstHW/src/main/resources/assets";
        String attribute = "year";
        int numberOfThreads = 5; // Кількість потоків для парсингу

        if (args.length == 0) {
            System.out.println("Please, enter directory path");
            return;
        } else if (args.length == 1) {
            path = args[0];
        } else if (args.length == 2) {
            path = args[0];
            attribute = args[1];
        }

        CarService carService = new CarService();
        long startTime = System.nanoTime();
        carService.getCountOfAttribute(path, attribute, numberOfThreads);
        long endTime = System.nanoTime();
        long durationInNanoseconds = endTime - startTime;
        double durationInSeconds = (double) durationInNanoseconds / 1_000_000_000;

        System.out.printf("Execution time: %.5f seconds%n", durationInSeconds);
    }
}
