package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class subscribeSecretSender {

    @Test
    @DisplayName("subscribe secret properties Sender")
    public void subscribePropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/subscribe/application-secret.properties",
                "integration-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("subscribe secret yml Sender")
    public void subscribeYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/subscribe/application.yml",
                "integration-service",
                "http://localhost:8080/api/receive-secret"
        );
    }
}
