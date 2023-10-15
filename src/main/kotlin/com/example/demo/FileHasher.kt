package com.example.demo

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.component.file.GenericFile

abstract class FileHasher : Processor {
    abstract fun calculateHash(path: String): String

    override fun process(exchange: Exchange?) {
        val body = exchange?.`in`?.body as GenericFile<*>

        exchange.setProperty("hash", calculateHash(body.absoluteFilePath))
    }
}