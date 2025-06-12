package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class chatbotSecretSender {

    @Test
    @DisplayName("챗봇 secret properties Sender")
    public void chatbotPropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/chatbot/application-secret.properties",
                "chatbot-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("챗봇 secret yml Sender")
    public void chatbotYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/chatbot/application.yml",
                "chatbot-service",
                "http://localhost:8080/api/receive-secret"
        );
    }
}
