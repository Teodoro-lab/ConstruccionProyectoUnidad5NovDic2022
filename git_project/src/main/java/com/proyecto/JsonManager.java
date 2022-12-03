package com.proyecto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonManager {

    private String fileName = "src/employees.json";

    private void updateEmployeeFrom(Employee e, JSONArray employees) {
        for (int i = 0; i < employees.size(); i++) {
            JSONObject employee = (JSONObject) employees.get(i);
            if (employee.get("id").equals(e.getId())) {
                employee.put("firstName", e.getFirstName());
                employee.put("lastName", e.getLastName());
                employee.put("photo", e.getPhoto());
            }
        }
    }

    public void deleteEmployeeFromJson(String id) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(this.fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employees = (JSONArray) jsonObject.get("employees");

            for (int i = 0; i < employees.size(); i++) {
                JSONObject employeeObj = (JSONObject)employees.get(i);
                if (employeeObj.get("id").equals(id)){
                    employees.remove(i);
                    break;
                }
            }

            jsonObject.put("employees", employees);


            FileWriter file = new FileWriter(this.fileName);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e1) {
            e1.printStackTrace();
        }

    }

    public void updateEmployeeInJson(Employee e) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(this.fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employees = (JSONArray) jsonObject.get("employees");
            jsonObject.put("employees", employees);

            updateEmployeeFrom(e, employees);

            FileWriter file = new FileWriter(this.fileName);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e1) {
            e1.printStackTrace();
        }

    }

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

    public static void main(String[] args) {
        JsonManager jsonManager = new JsonManager();
        jsonManager.deleteEmployeeFromJson("2");
    }
}
