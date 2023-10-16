package com.example.demo

import org.apache.camel.Exchange
import org.apache.camel.Processor

abstract class FileHasher : Processor {
    abstract fun calculateHash(path: String): String

    override fun process(exchange: Exchange?) {
        val path = exchange?.`in`?.getHeader("CamelFileAbsolutePath") as String

        exchange.setProperty("hash", calculateHash(path))
    }
}

