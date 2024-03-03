package br.com.wscastro.JWTProvider.Controllers;


import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class JwtValidationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void testJwtValidationCaso1() throws Exception {
        // Testando envio do token valido
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        mvc.perform(post("/auth/validate-jwt").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isOk());
    }

    @Test
    public void testJwtValidationCaso2() throws Exception {
        // Testando envio do token invalido
        String jwt = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        mvc.perform(post("/auth/validate-jwt").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isOk())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("false")));
    }

    @Test
    public void testJwtValidationCaso3() throws Exception {
        // Testando envio do token invalido
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
        mvc.perform(post("/auth/validate-jwt").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isOk())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("false")));
    }

    @Test
    public void testJwtValidationCaso4() throws Exception {
        // Testando envio do token invalido
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
        mvc.perform(post("/auth/validate-jwt").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isOk())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("false")));
    }

    @Test
    public void testJwtValidation() throws Exception {
        // Testando nao envio do token
        mvc.perform(post("/auth/validate-jwt"))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    public void testJwtValidationCaso1WithException() throws Exception {
        // Testando envio do token valido
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        mvc.perform(post("/auth/validate-jwt/with-exceptions").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isOk())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("true")));
    }

    @Test
    public void testJwtValidationCaso2WithException() throws Exception {
        // Testando envio do token invalido
        String jwt = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        mvc.perform(post("/auth/validate-jwt/with-exceptions").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isForbidden())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("Invalid jwt payload Json")));
    }

    @Test
    public void testJwtValidationCaso3WithException() throws Exception {
        // Testando envio do token invalido
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
        mvc.perform(post("/auth/validate-jwt/with-exceptions").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isForbidden())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("Invalid Name")));
    }

    @Test
    public void testJwtValidationCaso4WithException() throws Exception {
        // Testando envio do token invalido
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
        mvc.perform(post("/auth/validate-jwt/with-exceptions").contentType(MediaType.TEXT_PLAIN).content(jwt))
                .andExpect(status()
                        .isForbidden())
                .andExpect(result -> Assert.isTrue(result.getResponse().getContentAsString().contains("Json must have 3 fields")));
    }

    @Test
    public void testJwtValidationWithException() throws Exception {
        // Testando nao envio do token
        mvc.perform(post("/auth/validate-jwt/with-exceptions"))
                .andExpect(status()
                        .isBadRequest());
    }


}
