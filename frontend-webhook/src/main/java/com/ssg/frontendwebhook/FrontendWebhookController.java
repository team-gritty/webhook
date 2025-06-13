package com.ssg.frontendwebhook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class FrontendWebhookController {

    @PostMapping
    public ResponseEntity<String> deploy(@RequestBody Map<String, Object> payload) {
        try {
            String ref = (String) payload.get("ref");
            if (!"refs/heads/main".equals(ref)) {
                return ResponseEntity.ok("✅ dev 브랜치가 아니므로 무시됨");
            }

            // 1. 해당 디렉토리로 이동 후 pull
            String[] cmd = {
                    "/bin/sh",
                    "-c",
                    "cd /home/project/linki && git pull origin dev && sudo reboot"
            };
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            return ResponseEntity.ok("✅ pull + reboot 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ 실패: " + e.getMessage());
        }
    }
}
