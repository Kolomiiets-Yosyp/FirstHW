package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Statistics extends  Thread{
    private final JsonFactory factory = new JsonFactory();



    public void stat(File file, String attribute) throws IOException {
        Map<String, Integer> brandCounts = new HashMap<>();

        try (JsonParser parser = factory.createParser(file)) {
            // Перевіряємо, чи починається JSON з масиву
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected an array");
            }

            // Проходимо кожен об'єкт масиву
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                // Перевіряємо, чи починається об'єкт
                if (parser.currentToken() != JsonToken.START_OBJECT) {
                    throw new IllegalStateException("Expected an object");
                }

                String brand = null;
                // Починаємо розбір об'єкта
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    // Перевіряємо, чи це поле, яке нас цікавить
                    if (parser.currentToken() == JsonToken.FIELD_NAME && parser.getText().equals(attribute)) {
                        parser.nextToken(); // Переходимо до значення атрибута
                        brand = parser.getText();
                    }
                }

                // Збільшуємо лічильник для марки, якщо вона є
                if (brand != null) {
                    brandCounts.put(brand, brandCounts.getOrDefault(brand, 0) + 1);
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        File xmlFile = new File("statistics_by_" + attribute + ".xml");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable output indentation
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<statistics>\n");

        for (Map.Entry<String, Integer> entry : brandCounts.entrySet()) {
            xmlBuilder.append("  <item>\n");
            xmlBuilder.append("    <attributeValue>").append(entry.getKey()).append("</attributeValue>\n");
            xmlBuilder.append("    <count>").append(entry.getValue()).append("</count>\n");
            xmlBuilder.append("  </item>\n");
        }

        xmlBuilder.append("</statistics>\n");
        xmlMapper.writeValue(xmlFile, xmlBuilder.toString());


    }




}

