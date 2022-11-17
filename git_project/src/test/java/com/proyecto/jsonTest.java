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
    public void JSONNotExistence_ThrowFileNotFoundException() {
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
    public void NotValidJSON_ThrowParseException() {
        assertThrows(ParseException.class, () -> {
            try {
                JsonValidation.readJson();
            } catch (IllegalArgumentException | IOException e) {
                assumeNoException(e);
            }
        });
    }

    @Test
    @DisplayName("Validate function throws IllegalArgumentException when JSON file is missing a required key")
    public void JSONSchemaValidator_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                JsonValidation.readJson();
            } catch (ParseException | IOException e) {
                assumeNoException(e);
            }
        });
    }

    @Test
    @DisplayName("Validate that the that the json output string is correct")
    public void validateOutputString() {

        String expetedString = "{\"employees\":[" +
                "{\"firstName\":\"Tom\",\"lastName\":\"Cruise\",\"photo\":\"https:\\/\\/jsonformatter.org\\/img\\/tom-cruise.jpg\",\"id\":\"1\"},"
                +
                "{\"firstName\":\"Maria\",\"lastName\":\"Sharapova\",\"photo\":\"https:\\/\\/jsonformatter.org\\/img\\/Maria-Sharapova.jpg\",\"id\":\"2\"},"
                +
                "{\"firstName\":\"Robert\",\"lastName\":\"Downey Jr.\",\"photo\":\"https:\\/\\/jsonformatter.org\\/img\\/Robert-Downey-Jr.jpg\",\"id\":\"3\"}"
                +
                "]}";

        try {
            assertEquals(expetedString, JsonValidation.readJson());
        } catch (ParseException | IllegalArgumentException | IOException e) {
            assumeNoException(e);
        }
    }
}
