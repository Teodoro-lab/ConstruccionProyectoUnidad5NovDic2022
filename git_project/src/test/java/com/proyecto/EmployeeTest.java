package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class EmployeeTest {

<<<<<<< HEAD
=======
    private String employeesJSONContent;
    private JsonReader employeeManager;

    @BeforeAll
    public void readEmployeesFromJSON() throws IOException, ParseException {
        this.employeesJSONContent = JsonValidation.readJson();
    }

    @BeforeEach
    public void createEmployeesManager() {
        this.employeeManager = new JsonReader();
    }

>>>>>>> d5fa2b9cdac9dca5e24e054558ce3e8525b8221b
    @Test
    @DisplayName("Json convertion")
    public void shouldCreateArrayOfEmployees() {
        JsonReader jsonReader = new JsonReader();
        assertFalse(jsonReader.convertJsonToList() == null);
        assertEquals(3, jsonReader.convertJsonToList().length);
    }
}