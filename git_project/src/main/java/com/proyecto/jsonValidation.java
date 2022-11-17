package com.proyecto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class jsonValidation {

    private static void validateJSONEmployeeData(JSONObject employeeObject) {
        if (
            !employeeObject.containsKey("id") ||
            !employeeObject.containsKey("firstName") ||
            !employeeObject.containsKey("lastName") ||
            !employeeObject.containsKey("Photo")) 
        {
            throw new IllegalArgumentException("An employee object is missing a required field");
        }
    }

    private static void validateCorrectJsonData(JSONObject json) {
        JSONArray employees;

        if (json.containsKey("employees")) {
            employees = (JSONArray) json.get("employees");

            for (Object employee : employees) {
                JSONObject employeeObject = (JSONObject) employee;
                validateJSONEmployeeData(employeeObject);
            }

        } else {
            throw new IllegalArgumentException("Json file is missing the employees key");
            }
        }

    public static String readJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/employees.json");

        Object objectToRead = parser.parse(reader);
        JSONObject json = (JSONObject) objectToRead;
        validateCorrectJsonData(json);

        return json.toJSONString();
    }
}