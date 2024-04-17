package com.profitsoft.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilService {
    private final JsonParser jsonParser = new JsonParser();

    public List<String> getJsonFilesList(String directoryPath) {
        try {
            try (var pathStream = Files.walk(Path.of(directoryPath))) {
                return pathStream
                        .filter(Files::isRegularFile)
                        .map(Path::toString)
                        .filter(string -> string.endsWith(".json"))
                        .toList();
            }
        } catch (IOException e) {
            System.out.println("Error getting JSON files list: " + e.getMessage());
            return List.of();
        }
    }

    public <T> List<T> parseAll(List<String> jsonPaths, Class<T> className, int numberOfThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<T> itemList = new ArrayList<>();
        List<Callable<Void>> tasks = new ArrayList<>();

        jsonPaths.forEach(path -> tasks.add(() -> {
            try {
                List<?> items = jsonParser.parse(path, className);
                if (items != null) {
                    itemList.addAll(filterAndCastItems(items, className));
                }
            } catch (Exception e) {
                System.out.println("Error parsing JSON file " + path + ": " + e.getMessage());
            }
            return null;
        }));

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            System.out.println("Error in task execution: " + e.getMessage());
        }

        executor.shutdown();
        return itemList;
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> filterAndCastItems(List<?> items, Class<T> className) {
        return items.stream()
                .filter(className::isInstance)
                .map(className::cast)
                .toList();
    }
}
