package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdminIntegrationSecretSender {

    @Test
    @DisplayName("관리자 secret properties Sender")
    public void adminPropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/admin-integration/application-secret.properties",
                "admin-integration-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("관리자 secret yml Sender")
    public void adminYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/admin-integration/application.yml",
                "admin-integration-service",
                "http://localhost:8080/api/receive-secret"
        );
    }


}
