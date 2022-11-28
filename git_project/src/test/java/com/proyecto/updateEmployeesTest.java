package com.proyecto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class updateEmployeesTest {

    @Test
    public void updateEmployees() {
        boolean exists = false;

        Employee newEmployee = new Employee("3", "Bruice", "Lee", "https://jsonformatter.org/img/tom-cruise.jpg");

        JsonManager manager = new JsonManager();
        for (Employee employee : manager.convertJsonToList()) {
            if (employee.getId().equals(newEmployee.getId())) {
                exists = true;
                break;
            }
        }

        assertTrue(exists);
        assertFalse(newEmployee.getFirstName().isBlank());
        assertFalse(newEmployee.getLastName().isBlank());
        assertFalse(newEmployee.getPhoto().isBlank());
        manager.updateEmployeeInJson(newEmployee);

    }

}
