package org.example;

import java.io.File;

public class PathHandler extends Thread {

    private final File[] files;
    private final String attribute;

    public PathHandler(File[] files, String attribute) {
        this.files = files;
        this.attribute = attribute;
    }

    @Override
    public void run() {
        Statistics statistics = new Statistics();
        for (File file : files) {
            // Перевіряємо, чи файл є JSON-файлом
            if (file.isFile() && file.getName().endsWith(".json")) {
                // Обробляємо кожний JSON-файл
                try {
                    System.out.println("Statistics for file: " + file.getName());
                    statistics.stat(file, attribute);
                    System.out.println();

                } catch (Exception e) {
                    System.err.println("Error processing file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }


}
