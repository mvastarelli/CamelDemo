package com.example.demo

import org.apache.camel.Endpoint
import org.apache.camel.Exchange
import org.apache.camel.support.DefaultProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Files

class HashProducer(
    endpoint: Endpoint,
    private val directoryName: String,
    private val hasher: FileHasher,
    private val logger: Logger = LoggerFactory.getLogger(HashProducer::class.java)
    ) : DefaultProducer(endpoint) {
    override fun process(exchange: Exchange?) {
        val sourcePath = File(exchange?.`in`?.body.toString())
        val targetPath = File("${sourcePath.name}.sha1", directoryName)
        val hash = hasher.calculateHash(sourcePath.absolutePath)

        logger.info("Writing hash: $hash to $targetPath")
        Files.writeString(targetPath.toPath(), hash)
    }
}