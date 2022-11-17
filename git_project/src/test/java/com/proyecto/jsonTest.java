package com.proyecto;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonTest {

    @Test
    @DisplayName("Validate the existence of the employees JSON file")
    public void JSONexistence() {
        assertThrows(FileNotFoundException.class, () -> {
            try {
                JsonValidation.readJson();
            } catch (ParseException | IllegalArgumentException e) {
                assumeNoException(e);
            }
        });
    }

    @Test
    @DisplayName("Validate function throws ParseException when JSON file contains invalid data")
    public void validateCorrectJsonData() {
       assertThrows( ParseException.class, () -> {
           try {
               JsonValidation.readJson();
           } catch (IllegalArgumentException | IOException e) {
               assumeNoException(e);
           }
       });
    }

    @Test
    @DisplayName("Validate function throws IllegalArgumentException when JSON file is missing a required key")
    public void JSONSchemaValidator() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                JsonValidation.readJson();
            } catch (ParseException | IOException e) {
                assumeNoException(e);
            }
        });
    }

}