package com.proyecto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class jsonValidation {

    public static String readJson() {
        Object objectToRead;

        {
            try {
                objectToRead = new JSONParser().parse(new FileReader("src/employees.json"));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        JSONObject jsonA = (JSONObject) objectToRead;
        return jsonA.toString();
    }
}