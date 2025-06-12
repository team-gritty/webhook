package com.gritty.secretsender.secretsender;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class subscribeSecretSender {

    @Test
    @DisplayName("subscribe secret properties Sender")
    public void subscribePropertiesSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/subscribe/application-secret.properties",
                "integration-service",
                url
        );
    }

    @Test
    @DisplayName("subscribe secret yml Sender")
    public void subscribeYmlSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/subscribe/application.yml",
                "integration-service",
                url
        );
    }
}
