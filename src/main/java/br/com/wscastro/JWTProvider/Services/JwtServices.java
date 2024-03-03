package br.com.wscastro.JWTProvider.Services;

import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class JwtServices {



    public static List<String> parseWithoutValidation(String jwt) throws Exception {
        String base64UrlEncodedHeader = null;
        String base64UrlEncodedPayload = null;
        List<String> headerAndPayloadDecoded = null;
        try{
            // Split the JWT and decode each part
            headerAndPayloadDecoded = JwtServices.splitJwt(jwt, base64UrlEncodedHeader, base64UrlEncodedPayload)
                    .stream()
                    .map(s -> (new String(Base64.getDecoder().decode(s))))
                    .toList();
        }catch (Exception e){
            throw new Exception("Invalid JWT: "+ e.getMessage());
        }

        return headerAndPayloadDecoded;
    }

    public static List<String> splitJwt(String jwt, String base64UrlEncodedHeader, String base64UrlEncodedPayload) throws Exception {
        int delimiterCount = 0;
        StringBuilder sb = new StringBuilder(128);
        // Converte a string JWT em um array de caracteres
        char[] arr = jwt.toCharArray();

        // iteração em cada caractere na string JWT
        for (char c : arr) {
            if (c == '.') {
                // limpa o StringBuilder e pega a sequência de caracteres do StringBuilder
                CharSequence tokenSeq = Strings.clean(sb);
                String token = tokenSeq != null ? tokenSeq.toString() : null;
                if (delimiterCount == 0) {
                    base64UrlEncodedHeader = token;
                } else if (delimiterCount == 1) {
                    base64UrlEncodedPayload = token;
                }
                ++delimiterCount;
                // limpa o StringBuilder para o proximo token
                sb.setLength(0);
            } else {
                // adiciona o caractere ao StringBuilder
                sb.append(c);
            }
        }
        if (delimiterCount != 2) {
            String msg = "JWT strings must contain exactly 2 period characters. Found: " + delimiterCount;
            throw new Exception(msg);
        }
        return List.of(new String[]{base64UrlEncodedHeader, base64UrlEncodedPayload});
    }
}
