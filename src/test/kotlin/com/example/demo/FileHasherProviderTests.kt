package com.example.demo

import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class FileHasherProviderTests {
    @Test
    fun returnsSha1HasherIfSpecified() {
        // Arrange.
        val provider = FileHasherProvider(HasherConfigurationProperties("sha1"))

        // Act.
        val result = provider.getHasher()

        // Assert.
        assertInstanceOf(SHA1FileHasher::class.java, result)
    }

    @ParameterizedTest
    @ValueSource(strings = [ "", "md5", "other" ])
    fun returnsMD5HasherByDefault(algorithm: String) {
        // Arrange.
        val provider = FileHasherProvider(HasherConfigurationProperties(algorithm))

        // Act.
        val result = provider.getHasher()

        // Assert.
        assertInstanceOf(MD5FileHasher::class.java, result)
    }
}