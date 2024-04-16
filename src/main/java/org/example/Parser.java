package org.example;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public void addInfo() throws IOException {

        JsonFactory factory = new JsonFactory();
        File file = new File("output.json");

        try (JsonParser parser = factory.createParser(file)) {
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    parser.nextToken();// переходимо до значення
                    System.out.println(fieldName + ": " + parser.getText());

                }
            }
        }
    }
}