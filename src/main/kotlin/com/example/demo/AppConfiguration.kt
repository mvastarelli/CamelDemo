package com.example.demo

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(HasherConfigurationProperties::class)
class AppConfiguration {
    @Bean
    fun hashSettings(): HasherConfigurationProperties {
        return HasherConfigurationProperties()
    }
}