package com.example.demo

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FileHasherProvider (
    private val hashSettings: HasherConfigurationProperties,

    @Value("\${hasher.algorithm}")
    private val hashAlgo : String
) {
    @Bean("hasher")
    fun getHasher(): FileHasher {
        if(hashSettings.algorithm == "sha1" ) {
            return SHA1FileHasher()
        }

        return MD5FileHasher()
    }
}