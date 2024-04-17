package com.profitsoft.utils;

import com.profitsoft.entity.Car;
import com.profitsoft.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class JsonParser {

    public List<?> parse(String path, Class<?> className) throws IOException {
        if (className == null) {
            throw new NullPointerException("Class cannot be null");
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            return parseJsonToObjectList(reader, className);
        }
    }

    private <T> List<T> parseJsonToObjectList(BufferedReader reader, Class<T> valueType) {
        List<T> objects = new ArrayList<>();
        StringBuilder jsonBuilder = new StringBuilder();
        reader.lines().forEach(jsonBuilder::append);
        JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            objects.add(createObjectFromJson(jsonObject, valueType));
        }
        return objects;
    }

    private <T> T createObjectFromJson(JSONObject jsonObj, Class<T> valueType) {
        if (valueType == User.class) {
            String userName = jsonObj.getString("userName");
            int phoneNumber = jsonObj.getInt("phoneNumber");
            Date joinDate = Date.valueOf(jsonObj.getString("joinDate"));
            return valueType.cast(new User(userName, phoneNumber, joinDate));
        }
        if (valueType == Car.class) {
            String brand = jsonObj.getString("brand");
            String model = jsonObj.getString("model");
            int year = jsonObj.getInt("year");
            return valueType.cast(new Car(brand, model, year));
        }
        return null;
    }


}
