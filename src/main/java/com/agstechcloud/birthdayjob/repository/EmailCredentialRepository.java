package com.agstechcloud.birthdayjob.repository;

import com.agstechcloud.birthdayjob.entity.EmailCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailCredentialRepository extends JpaRepository<EmailCredentialEntity, Long> {
    EmailCredentialEntity findByIsActiveTrue();
}
