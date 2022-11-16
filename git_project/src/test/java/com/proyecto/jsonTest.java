package com.proyecto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class jsonTest {

    @Test
    @DisplayName("Existencia del archivo JSON")

    public void JSONexistencia() {
        File archivo = new File("src/employees.json");
        assertTrue(archivo.exists());
    }

    @Test
    @DisplayName("Validacion del archivo JSON")

    public void JSONValidator() {

        JSONParser par = new JSONParser();

        String readtxtJSON = jsonValidation.readJson();
        try {
            JSONObject jsonObj = new JSONObject((JSONObject) par.parse(readtxtJSON));

            assertTrue(jsonObj.containsKey("employees"));

        } catch (ParseException e) {
            fail("La validacion falló: " + e.getMessage());
        }

    }

}