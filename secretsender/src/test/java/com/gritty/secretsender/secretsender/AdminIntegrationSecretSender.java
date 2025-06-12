package com.gritty.secretsender.secretsender;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class AdminIntegrationSecretSender {

    @Test
    @DisplayName("관리자 secret properties Sender")
    public void adminPropertiesSecretSender() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();


        SecretFileSender.sendSecret(
                "src/test/resources/admin-integration/application-secret.properties",
                "admin-integration-service",
                url
        );
    }

    @Test
    @DisplayName("관리자 secret yml Sender")
    public void adminYmlSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/admin-integration/application.yml",
                "admin-integration-service",
                url
        );
    }


}
