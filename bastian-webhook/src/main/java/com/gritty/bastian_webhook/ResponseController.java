package com.gritty.bastian_webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/webhook")
public class ResponseController {

    @PostMapping("/github")
    public ResponseEntity<String> receiveGithubWebhook(@RequestBody Map<String, Object> payload) {
        try {
            // ✅ 1. main 브랜치에서 푸시된 건지 확인
            String ref = (String) payload.get("ref");
            if (ref == null || !ref.equals("refs/heads/main")) {
                return ResponseEntity.ok("Not main branch. Ignored.");
            }

            // ✅ 2. commits에서 파일 변경 목록 추출
            List<Map<String, Object>> commits = (List<Map<String, Object>>) payload.get("commits");
            if (commits == null || commits.isEmpty()) {
                return ResponseEntity.ok("No commits found.");
            }

            Set<String> affectedPackages = new HashSet<>();
            for (Map<String, Object> commit : commits) {
                List<String> modified = (List<String>) commit.get("modified");
                List<String> added = (List<String>) commit.get("added");
                List<String> removed = (List<String>) commit.get("removed");

                Stream.of(modified, added, removed)
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .map(path -> path.split("/")[0]) // 최상위 폴더
                        .forEach(affectedPackages::add);
            }

            // ✅ 3. 변경된 폴더에 따라 각기 다른 서버로 웹훅 전달
            for (String pkg : affectedPackages) {
                String targetUrl = switch (pkg) {
                    case "discovery-service" -> " http://10.0.2.6:8888/webhook/github";
                    case "integration-service" -> "http://10.0.2.9:8888/webhook/github";
                    case "admin-integration-service" -> "http://10.0.0.10:8888/webhook/github";
                    case "payment-service" -> "http://10.0.2.11:8888/webhook/github";
                    case "subscribe-service" -> "http://10.0.2.12:8888/webhook/github";
                    case "chatbot-service" -> "http://10.0.2.8:8888/webhook/github";
                    case "chat-service" -> "http://10.0.2.7:8888/webhook/github";
                    default -> null;
                };

                if (targetUrl == null) continue;

                java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
                java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                        .uri(new java.net.URI(targetUrl))
                        .header("Content-Type", "application/json")
                        .POST(java.net.http.HttpRequest.BodyPublishers.ofString(
                                new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(payload)))
                        .build();

                client.sendAsync(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            }

            return ResponseEntity.ok("Webhook forwarded to: " + affectedPackages);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
