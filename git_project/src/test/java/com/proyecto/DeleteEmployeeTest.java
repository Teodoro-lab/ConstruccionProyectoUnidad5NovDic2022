package com.proyecto;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class DeleteEmployeeTest {
    @Test
    public void deleteEmployees() {
        boolean exists = true;

        JsonManager manager = new JsonManager();
        Employee employeeToDelete = manager.convertJsonToList()[0];

        manager.deleteEmployeeFromJson(employeeToDelete.getId());
        exists = false;

        for (Employee employee : manager.convertJsonToList()) {
            if (employee.getId().equals(employeeToDelete.getId())) {
                exists = true;
            }
        }

        assertFalse(exists);
    }
}