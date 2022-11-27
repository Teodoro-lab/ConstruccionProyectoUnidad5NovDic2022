package com.proyecto;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    private Employee[] getEmployeeList(JSONObject json) {
        JSONArray employees = (JSONArray) json.get("employees");
        Employee[] employeeList = new Employee[employees.size()];

        for (int i = 0; i < employees.size(); i++) {
            JSONObject employee = (JSONObject) employees.get(i);

            String id = (String) employee.get("id");
            String firstName = (String) employee.get("firstName");
            String lastName = (String) employee.get("lastName");
            String photo = (String) employee.get("photo");
            
            employeeList[i] = new Employee(id, firstName, lastName, photo);
        }

        return employeeList;
    }

    public Employee[] convertJsonToList() {

        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/employees.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            return getEmployeeList(jsonObject);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return new Employee[] {};
    }
}
