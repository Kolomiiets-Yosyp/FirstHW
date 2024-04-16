package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        File folder = new File("/Users/imac/Desktop/FirstHW");
        String attribute = "brand";

        // Отримуємо список файлів у папці
        File[] files = folder.listFiles();

        // Створюємо об'єкт PathHandler та запускаємо його в новому потоці
        Thread handlerThread = new PathHandler(files, attribute);
        handlerThread.start();
    }
}