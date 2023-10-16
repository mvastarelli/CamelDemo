package com.example.demo

import org.apache.camel.CamelContext
import org.apache.camel.Endpoint
import org.apache.camel.spi.annotations.Component
import org.apache.camel.support.DefaultComponent
import org.springframework.beans.factory.annotation.Autowired

@Component("filehash")
class FileHashingComponent(
    context: CamelContext,
    @Autowired private val hasher : FileHasher
): DefaultComponent(context) {
    override fun createEndpoint(uri: String?, remaining: String?, parameters: MutableMap<String, Any>?): Endpoint {
        return uri?.let { FileHashingEndpoint(it, this) } as Endpoint
    }
}