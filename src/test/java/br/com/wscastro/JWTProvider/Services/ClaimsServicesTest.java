package br.com.wscastro.JWTProvider.Services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class ClaimsServicesTest {

    @Test
    void testValidJson() throws Exception {
        String jsonString = "{\"Name\": \"John Doe\", \"Role\": \"Admin\", \"Seed\": \"17\"}";
        assertTrue(ClaimsServices.validateJson(jsonString));
    }

    @Test
    void testInvalidJsonFormat() {
        String jsonString = "InvalidJsonFormat";
        assertThrows(JsonParseException.class, () -> ClaimsServices.validateJson(jsonString));
    }

    @Test
    void testInvalidJsonFields() {
        String jsonString = "{\"InvalidField\": \"Value\"}";
        assertThrows(Exception.class, () -> ClaimsServices.validateJson(jsonString));
    }

    @Test
    void testInvalidNameField() {
        String jsonString = "{\"Name\": \"John123\", \"Role\": \"Admin\", \"Seed\": \"17\"}";
        assertThrows(Exception.class, () -> ClaimsServices.validateJson(jsonString));
    }

    @Test
    void testInvalidRoleField() {
        String jsonString = "{\"Name\": \"John Doe\", \"Role\": \"InvalidRole\", \"Seed\": \"17\"}";
        assertThrows(Exception.class, () -> ClaimsServices.validateJson(jsonString));
    }

    @Test
    void testInvalidSeedField() {
        String jsonString = "{\"Name\": \"John Doe\", \"Role\": \"Admin\", \"Seed\": \"NotAPrimeNumber\"}";
        assertThrows(Exception.class, () -> ClaimsServices.validateJson(jsonString));
    }

    @Test
    void testMockedJsonNode() throws Exception {
        String jsonString = """
                {
                    "Name": "John Doe",
                    "Role": "Admin",
                    "Seed": "17"
                }
                """;

        assertTrue(ClaimsServices.validateJson(jsonString));
    }
}
