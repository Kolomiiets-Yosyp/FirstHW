package com.profitsoft.service;

public class TimeCount {
    public void getTime(String path,String attribute, int numberOfThreads){
        CarService carService = new CarService();
        long startTime = System.nanoTime();
        carService.getCountOfAttribute(path, attribute, numberOfThreads);
        long endTime = System.nanoTime();
        long durationInNanoseconds = endTime - startTime;
        double durationInSeconds = (double) durationInNanoseconds / 1_000_000_000;

        System.out.println("Execution time: "+durationInSeconds+" seconds");
    }
}
