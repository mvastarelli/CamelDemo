package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FileHasherProvider (
    private val hashSettings: HasherConfigurationProperties,
) {
    @Bean("hasher")
    fun getHasher(): FileHasher {
        if(hashSettings.algorithm == "sha1" ) {
            return SHA1FileHasher()
        }

        return MD5FileHasher()
    }
}