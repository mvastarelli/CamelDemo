package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="hasher")
data class HasherConfigurationProperties (
    var algorithm: String = "",
    var encoding: String = "hex"
)