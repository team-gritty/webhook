package com.gritty.secretsender.secretsender;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class chatbotSecretSender {

    @Test
    @DisplayName("챗봇 secret properties Sender")
    public void chatbotPropertiesSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/chatbot/application-secret.properties",
                "chatbot-service",
                url
        );
    }

    @Test
    @DisplayName("챗봇 secret yml Sender")
    public void chatbotYmlSecretSender() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode config = mapper.readTree(new File("src/test/resources/config.yml"));
        String url = config.get("secret").get("url").asText();

        SecretFileSender.sendSecret(
                "src/test/resources/chatbot/application.yml",
                "chatbot-service",
                url
        );
    }
}
