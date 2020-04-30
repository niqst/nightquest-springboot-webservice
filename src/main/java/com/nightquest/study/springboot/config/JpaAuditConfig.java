package com.nightquest.study.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  //jpa audit 활성화
public class JpaAuditConfig {
}
