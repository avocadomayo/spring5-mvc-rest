package ca.wendyliu.springframework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A helper class for testing.
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object object) {
        try {
            // Use Jackson to return a JSON String.
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
