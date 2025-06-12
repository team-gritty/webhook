package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class chatSecretSender {

    @Test
    @DisplayName("채팅 secret properties Sender")
    public void chatPropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/chat/application-secret.properties",
                "chat-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("채팅 secret yml Sender")
    public void chatYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/chat/application.yml",
                "chat-service",
                "http://localhost:8080/api/receive-secret"
        );
    }
}
