package com.example.demo

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class FileMover : RouteBuilder() {
    override fun configure() {
        from("file://C:/temp/in")
            .id("file-mover-kt")
            .log("Received \${body.fileName}")
            .process("hasher")
            .log("File Hash: \${exchangeProperty.hash}")
            .to("file://C:/temp/out")
            .setBody(simple("\${exchangeProperty.hash}"))
            .to("file://C:/temp/out?fileName=\${file:name}.sha1")
    }
}