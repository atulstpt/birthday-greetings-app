package com.agstechcloud.birthdayjob.controller;

import com.agstechcloud.birthdayjob.dto.EmailCredentialRequest;
import com.agstechcloud.birthdayjob.dto.EmailConfigResponse;
import com.agstechcloud.birthdayjob.service.EmailCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email-configs")
public class EmailCredentialController {
    @Autowired
    private EmailCredentialService emailConfigService;

    @PostMapping
    public ResponseEntity<EmailConfigResponse> create(@RequestBody EmailCredentialRequest request) {
        EmailConfigResponse response = emailConfigService.createEmailConfig(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailConfigResponse> update(@PathVariable Long id, @RequestBody EmailCredentialRequest request) {
        EmailConfigResponse response = emailConfigService.updateEmailConfig(id, request);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = emailConfigService.deleteEmailConfig(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmailConfigResponse>> getAll() {
        return ResponseEntity.ok(emailConfigService.getAllEmailConfigs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailConfigResponse> getById(@PathVariable Long id) {
        EmailConfigResponse response = emailConfigService.getEmailConfig(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }
}

