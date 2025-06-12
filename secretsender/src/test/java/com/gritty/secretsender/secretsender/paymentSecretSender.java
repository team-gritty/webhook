package com.gritty.secretsender.secretsender;

import com.gritty.secretsender.SecretFileSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class paymentSecretSender {

    @Test
    @DisplayName("payment secret properties Sender")
    public void paymentPropertiesSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/payment/application-secret.properties",
                "subscribe-service",
                "http://localhost:8080/api/receive-secret"
        );
    }

    @Test
    @DisplayName("payment secret yml Sender")
    public void paymentYmlSecretSender() {
        SecretFileSender.sendSecret(
                "src/test/resources/payment/application.yml",
                "subscribe-service",
                "http://localhost:8080/api/receive-secret"
        );
    }
}
