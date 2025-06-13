package com.gritty.payment_webhook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheck {

    @PostMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
