package br.com.wscastro.JWTProvider.Controllers;

import br.com.wscastro.JWTProvider.Services.ClaimsServices;
import br.com.wscastro.JWTProvider.Services.JwtServices;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.lang.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class JwtValidationApplication {


    @PostMapping("/validate-jwt")
    public Boolean validateJwt(@RequestBody String jwt) {
        try {
            List<String> jwtParsedToString = JwtServices.parseWithoutValidation(jwt);
            return ClaimsServices.validateJson(jwtParsedToString.get(1));
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/validate-jwt/with-exceptions")
    public ResponseEntity validateJwtWithExceptions(@RequestBody String jwt) {
        try {
            List<String> jwtParsedToString = JwtServices.parseWithoutValidation(jwt);
            return ResponseEntity.ok(ClaimsServices.validateJson(jwtParsedToString.get(1)));
        } catch (Exception e){
            return ResponseEntity.status(403)
                    .body(e.getMessage());
        }
    }

}
