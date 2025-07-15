package com.agstechcloud.birthdayjob.service;

import com.agstechcloud.birthdayjob.entity.EmailCredentialEntity;
import com.agstechcloud.birthdayjob.dto.EmailCredentialRequest;
import com.agstechcloud.birthdayjob.dto.EmailConfigResponse;
import com.agstechcloud.birthdayjob.repository.EmailCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailCredentialService {
    @Autowired
    private EmailCredentialRepository emailConfigRepository;

    public EmailConfigResponse createEmailConfig(EmailCredentialRequest request) {
        EmailCredentialEntity config = new EmailCredentialEntity();
        config.setSenderEmail(request.getSenderEmail());
        config.setSenderName(request.getSenderName());
        config.setSmtpHost(request.getSmtpHost());
        config.setSmtpPort(request.getSmtpPort());
        config.setSmtpUsername(request.getSmtpUsername());
        config.setSmtpPassword(request.getSmtpPassword());
        config.setIsActive(request.getIsActive());
        config.setCreatedAt(LocalDateTime.now());
        config.setUpdatedAt(LocalDateTime.now());
        EmailCredentialEntity saved = emailConfigRepository.save(config);
        return toResponse(saved);
    }

    public EmailConfigResponse updateEmailConfig(Long id, EmailCredentialRequest request) {
        Optional<EmailCredentialEntity> optional = emailConfigRepository.findById(id);
        if (optional.isEmpty()) return null;
        EmailCredentialEntity config = optional.get();
        config.setSenderEmail(request.getSenderEmail());
        config.setSenderName(request.getSenderName());
        config.setSmtpHost(request.getSmtpHost());
        config.setSmtpPort(request.getSmtpPort());
        config.setSmtpUsername(request.getSmtpUsername());
        config.setSmtpPassword(request.getSmtpPassword());
        config.setIsActive(request.getIsActive());
        config.setUpdatedAt(LocalDateTime.now());
        EmailCredentialEntity saved = emailConfigRepository.save(config);
        return toResponse(saved);
    }

    public boolean deleteEmailConfig(Long id) {
        if (!emailConfigRepository.existsById(id)) return false;
        emailConfigRepository.deleteById(id);
        return true;
    }

    public List<EmailConfigResponse> getAllEmailConfigs() {
        return emailConfigRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EmailConfigResponse getEmailConfig(Long id) {
        return emailConfigRepository.findById(id).map(this::toResponse).orElse(null);
    }

    private EmailConfigResponse toResponse(EmailCredentialEntity config) {
        EmailConfigResponse response = new EmailConfigResponse();
        response.setId(config.getId());
        response.setSenderEmail(config.getSenderEmail());
        response.setSenderName(config.getSenderName());
        response.setSmtpHost(config.getSmtpHost());
        response.setSmtpPort(config.getSmtpPort());
        response.setSmtpUsername(config.getSmtpUsername());
        response.setIsActive(config.getIsActive());
        response.setCreatedAt(config.getCreatedAt());
        response.setUpdatedAt(config.getUpdatedAt());
        return response;
    }
}

