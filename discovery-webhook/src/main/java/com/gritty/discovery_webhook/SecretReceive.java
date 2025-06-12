package com.gritty.discovery_webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class SecretReceive {

    @PostMapping("/api/config")
    public ResponseEntity<String> saveSecretFile(@RequestPart("file") MultipartFile file) {
        try {
            // 원하는 저장 경로 지정
            //파일 경로 예시 : /home/project/linki/admin-integratin/src/main/resources/
            String filePath ="나중에 파일 경로 지정 ";
            File dest = new File(filePath + file.getOriginalFilename());
            file.transferTo(dest);
            return ResponseEntity.ok("파일 저장 완료: " + dest.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 실패: " + e.getMessage());
        }
    }
}
