package com.gritty.secretsender.secretsender;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class discoverySecretSender {

    @Test
    @DisplayName("discovery secret properties Sender")
    public void discoverPropertiesSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/discover/application-secret.properties",
                "discovery-service",
                url
        );
    }

    @Test
    @DisplayName("discovery secret yml Sender")
    public void discoverYmlSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/discover/application.yml",
                "discovery-service",
                url
        );
    }
}
