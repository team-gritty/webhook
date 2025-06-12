package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class discoverySecretSender {

    @Test
    @DisplayName("discovery secret properties Sender")
    public void discoverPropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/discover/application-secret.properties",
                "discovery-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("discovery secret yml Sender")
    public void discoverYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/discover/application.yml",
                "discovery-service",
                "http://localhost:8080/api/receive-secret"
        );
    }
}
