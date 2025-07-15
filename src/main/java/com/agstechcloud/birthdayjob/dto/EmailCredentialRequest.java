package com.agstechcloud.birthdayjob.dto;

import lombok.Data;

@Data
public class EmailCredentialRequest {
    private String senderEmail;
    private String senderName;
    private String smtpHost;
    private Integer smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private Boolean isActive;
}

