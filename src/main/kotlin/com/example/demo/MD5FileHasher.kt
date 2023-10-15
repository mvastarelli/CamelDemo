package com.example.demo

import org.springframework.stereotype.Component
import org.springframework.util.DigestUtils
import java.io.FileInputStream

@Component("md5-hasher")
class MD5FileHasher() : FileHasher() {
    override fun calculateHash(path: String): String {
        FileInputStream(path).use {
            return DigestUtils.md5DigestAsHex(it)
        }
    }
}
