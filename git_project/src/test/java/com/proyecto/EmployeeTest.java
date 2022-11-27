package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeTest {

    @Test
    @DisplayName("Json convertion")
    public void shouldCreateArrayOfEmployees() {
        JsonReader jsonReader = new JsonReader();
        assertFalse(jsonReader.convertJsonToList() == null);
        assertEquals(3, jsonReader.convertJsonToList().length);
    }
}