package br.com.wscastro.JWTProvider.Services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClaimsServices {
    private static final Set<String> VALID_ROLES = new HashSet<>();
    static {
        VALID_ROLES.add("Admin");
        VALID_ROLES.add("Member");
        VALID_ROLES.add("External");
    }

    public static boolean validateJson(String jsonString) throws Exception {
        try{
            //Convertendo json em String para objeto
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            // Verifica se há exatamente 3 campos
            validQuantityFields(jsonNode);
            // Validação do campo Name
            validNameField(jsonNode);
            // Validação do campo Role
            validRoleName(jsonNode);
            // Validação do campo Seed
            validSeedField(jsonNode);

        }catch (JsonParseException e){
            throw new JsonParseException("Invalid jwt payload Json");
        }
        return true;
    }

    private static void validSeedField(JsonNode jsonNode) throws Exception {
        // Validação do campo Seed
        JsonNode seedNode = jsonNode.get("Seed");
        if (seedNode == null || !isPrime(seedNode.asText())) {
            throw new Exception("Invalid Seed");
        }
    }

    private static void validRoleName(JsonNode jsonNode) throws Exception {
        // Validação do campo Role
        JsonNode roleNode = jsonNode.get("Role");
        if (roleNode == null || !VALID_ROLES.contains(roleNode.asText())) {
            throw new Exception("Invalid Role");
        }
    }

    private static void validNameField(JsonNode jsonNode) throws Exception {
        // Validação do campo Name
        JsonNode nameNode = jsonNode.get("Name");
        if (nameNode == null || nameNode.asText().isEmpty() || nameNode.asText().length() > 256 || containsDigit(nameNode.asText())) {
            throw new Exception("Invalid Name");
        }
    }

    private static void validQuantityFields(JsonNode jsonNode) throws Exception {
        // Verifica se há exatamente 3 campos
        if (jsonNode.size() != 3) {
            throw new Exception("Json must have 3 fields");
        }
    }


    private static boolean containsDigit(String s) {
        // Verifica se contém pelo menos um digito
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPrime(String s) {
        // Verifica se é primo
        try {
            int seed = Integer.parseInt(s);
            return BigInteger.valueOf(seed).isProbablePrime(1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
