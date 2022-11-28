package com.proyecto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class updateEmployeesTest {

    @Test
    @DisplayName("successfully update at employees data")
    public void shouldUpdateEmployeeData() {
        JsonManager jsonManager = new JsonManager();
        Employee[] info = jsonManager.convertJsonToList();
        Assertions.assertNotEquals(info, jsonManager.convertJsonToList());
    }

}
