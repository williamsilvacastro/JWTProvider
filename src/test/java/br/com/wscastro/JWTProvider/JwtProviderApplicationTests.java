package br.com.wscastro.JWTProvider;

import br.com.wscastro.JWTProvider.Controllers.JwtValidationApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class JwtProviderApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void testValidateJwtControllerValidToken() {
		//Caso 1
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
		Assert.isTrue(new JwtValidationApplication().validateJwt(jwt), "Jwt should be valid");

	}

	@Test
	void testValidateJwtControllerBrokenToken() {
		//Caso 2
		String jwt = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
		Assert.isTrue(!new JwtValidationApplication().validateJwt(jwt), "Jwt should be invalid");

	}

	@Test
	void testValidateJwtControllerNameWithNumbers() {
		//Caso 3
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
		Assert.isTrue(!new JwtValidationApplication().validateJwt(jwt), "Jwt should be invalid");

	}

	@Test
	void testValidateJwtControllerTokenWithMoreThanThreeFields() {
		//Caso 4
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
		Assert.isTrue(!new JwtValidationApplication().validateJwt(jwt), "Jwt should be invalid");

	}

}
