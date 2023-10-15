package com.example.demo

import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path
import java.security.MessageDigest

@Component("sha1-hasher")
class SHA1FileHasher: FileHasher() {
    override fun calculateHash(path: String): String {
        val sha1 = MessageDigest.getInstance("SHA-1")
        val filePath = Path.of(path)
        val data = Files.readAllBytes(filePath)
        val hashed = sha1.digest(data)
        val builder = StringBuilder()

        hashed.forEach {
            builder.append(String.format("%02x", it))
        }

        return builder.toString()
    }
}
