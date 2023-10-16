package com.example.demo

import org.apache.camel.Category
import org.apache.camel.Consumer
import org.apache.camel.Processor
import org.apache.camel.Producer
import org.apache.camel.spi.Metadata
import org.apache.camel.spi.UriEndpoint
import org.apache.camel.spi.UriParam
import org.apache.camel.support.DefaultEndpoint

@UriEndpoint(
    firstVersion = "1.0.0",
    scheme = "1.0.0",
    title="FileHash",
    syntax = "filehash:directoryName",
    category = [Category.FILE])
class FileHashingEndpoint(uri: String, component: org.apache.camel.Component) : DefaultEndpoint(uri, component) {
    @UriParam(name="directoryName")
    @Metadata(required=true)
    lateinit var directoryName : String

    lateinit var hasher: FileHasher

    override fun createProducer(): Producer {
        return HashProducer(this, directoryName, hasher)
    }

    override fun createConsumer(processor: Processor?): Consumer {
        TODO("Not yet implemented")
    }
}