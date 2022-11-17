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
