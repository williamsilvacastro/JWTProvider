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
        char[] arr$ = jwt.toCharArray();
        int len$ = arr$.length;
        String  token = null;

        for (char c : arr$) {
            if (c == '.') {
                CharSequence tokenSeq = Strings.clean(sb);
                token = tokenSeq != null ? tokenSeq.toString() : null;
                if (delimiterCount == 0) {
                    base64UrlEncodedHeader = token;
                } else if (delimiterCount == 1) {
                    base64UrlEncodedPayload = token;
                }
                ++delimiterCount;
                sb.setLength(0);
            } else {
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
