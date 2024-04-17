package com.profitsoft;


import com.profitsoft.service.CarService;
import com.profitsoft.service.TimeCount;

public class Main {
    static String path = "/Users/imac/Desktop/FirstHW/src/main/resources/assets";
    static String attribute = "year";
    static int numberOfThreads = 5; // Кількість потоків для парсингу
    public static void main(String[] args) {


        if (args.length < 1 || args.length > 2) {
            System.out.println("Invalid number of arguments. Usage: java Application <directory_path> [<attribute_name>]");
            return;
        }

        path = args[0];
        if (args.length == 2) {
            attribute = args[1];
        }

        TimeCount timeCount = new TimeCount();
        timeCount.getTime(path,attribute,numberOfThreads);

    }

}
