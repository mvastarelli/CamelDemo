package com.example.demo

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class FileMover : RouteBuilder() {
    override fun configure() {
        from("file://C:/temp/in")
            .routeId("file-mover-kt")
            .log("Received \${body.fileName}")
            .multicast().to("direct:source-file", "direct:hash-file")

        from("direct:source-file")
            .routeId("source-copy")
            .log("Processing source file: \${body.fileName}")
            .to("file://C:/temp/out")

        from("direct:hash-file")
            .routeId("hash-copy")
//            .setBody(simple("\${headers.CamelFileAbsolutePath}"))
//            .log("Processing body: \${body}")
//            .to("filehash://C:/temp/out")
            .log("Processing hash file: \${body.fileName}")
            .process("hasher")
            .setBody(simple("\${exchangeProperty.hash}"))
            .log("File Hash: \${exchangeProperty.hash}")
            .to("file://C:/temp/out?fileName=\${file:name}.sha1")
    }
}

@Component
class DummyRoute : RouteBuilder() {
    override fun configure() {
        from("direct:start")
            .routeId("greetings-router")
            .setBody(constant("Hello World"))
            .to("file:output")
    }
}