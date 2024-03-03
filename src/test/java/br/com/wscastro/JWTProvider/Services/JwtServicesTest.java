package br.com.wscastro.JWTProvider.Services;


import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtServicesTest {

    @Test
    void testParseWithoutValidation() throws Exception {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        List<String> result = JwtServices.parseWithoutValidation(jwt);

        assertEquals(2, result.size());
        assertTrue(result.get(0).contains("HS256"));
        assertTrue(result.get(1).contains("John Doe"));
    }

    @Test
    void testParseWithoutValidationInvalidJwt() {
        String invalidJwt = "InvalidJwtString";
        assertThrows(Exception.class, () -> JwtServices.parseWithoutValidation(invalidJwt));
    }

    @Test
    void testSplitJwt() throws Exception {
        JwtServices jwtServices = new JwtServices();
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        List<String> result = JwtServices.splitJwt(jwt);

        assertEquals(2, result.size());
        assertTrue(new String(Base64.getDecoder().decode(result.get(0))).contains("HS256"));
        assertTrue(new String(Base64.getDecoder().decode(result.get(1))).contains("John Doe"));
    }

    @Test
    void testSplitJwtInvalidDelimiterCount() {
        JwtServices jwtServices = new JwtServices();
        String invalidJwt = "InvalidJwtString";
        assertThrows(Exception.class, () -> JwtServices.splitJwt(invalidJwt));
    }

}
