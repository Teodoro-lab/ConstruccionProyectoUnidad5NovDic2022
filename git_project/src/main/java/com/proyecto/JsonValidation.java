package com.proyecto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonValidation {

    /* 
     *   Checks that each employee JSONObject contains the required fields:
     *  - firstName
     *  - lastName
     *  - photo
     *  - id
     * 
     * OtherWise throws IllegalArgumentException
     */
    private static void validateJSONEmployeeData(JSONObject employeeObject) {
        if (!employeeObject.containsKey("id") ||
                !employeeObject.containsKey("firstName") ||
                !employeeObject.containsKey("lastName") ||
                !employeeObject.containsKey("photo")) {
            throw new IllegalArgumentException("An employee object is missing a required field");
        }
    }

    /*
     * Checks that the JSON file contains a list of employees,
     * which each of them is validated by validateJSONEmployeeData
     */
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
        FileReader jsonFile = new FileReader("src/employees.json");
        Object employeesData = parser.parse(jsonFile);
        JSONObject json = (JSONObject) employeesData;
        validateCorrectJsonData(json);

        return json.toJSONString();
    }



    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(JsonValidation.readJson());
    }
}