package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegrationSecretSender {

    @Test
    @DisplayName("유저 secret properties Sender")
    public void userPropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/integration/application.yml",
                "integration-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("유저 secret yml Sender")
    public void userYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/integration/application.yml",
                "integration-service",
                "http://localhost:8080/api/receive-secret"
        );
    }
}
