package com.agstechcloud.birthdayjob.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmailConfigResponse {
    private Long id;
    private String senderEmail;
    private String senderName;
    private String smtpHost;
    private Integer smtpPort;
    private String smtpUsername;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

