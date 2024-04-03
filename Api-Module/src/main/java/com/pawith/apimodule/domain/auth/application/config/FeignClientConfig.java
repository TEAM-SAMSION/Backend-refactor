package com.pawith.apimodule.domain.auth.application.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.pawith")
public class FeignClientConfig {
}
