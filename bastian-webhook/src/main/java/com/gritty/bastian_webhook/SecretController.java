package com.gritty.bastian_webhook;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class SecretController {
    @PostMapping("/api/receive-secret")
    public ResponseEntity<String> receiveSecret(
            @RequestPart("file") MultipartFile file,
            @RequestParam("target") String target,
            @RequestHeader("X-Auth-Token") String token
    ) {

        try {
            // 내부 서비스로 파일 전달
            String internalUrl = switch (target) {
                case "user-service" -> "http://10.0.0.1:8080/api/config";
                case "order-service" -> "http://10.0.0.2:8080/api/config";
                default -> null;
            };

            if (internalUrl == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid target");
            }

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(internalUrl, requestEntity, String.class);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File forwarding failed");
        }
    }
}
