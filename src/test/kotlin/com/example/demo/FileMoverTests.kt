package com.example.demo

import org.apache.camel.EndpointInject
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.AdviceWith
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.test.junit5.CamelTestSupport
import org.apache.camel.test.spring.junit5.CamelSpringBootTest
import org.apache.camel.test.spring.junit5.MockEndpoints
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@CamelSpringBootTest
@MockEndpoints("direct:*")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FileMoverTests : CamelTestSupport() {
    @Autowired lateinit var template: ProducerTemplate
    @EndpointInject("direct:out") lateinit var mock: MockEndpoint

    @Test
    @DirtiesContext
    fun foo() {
        mock.expectedBodiesReceived("foo")
        template.sendBody("file://C:/temp/in", "asdf")
        mock.assertIsSatisfied()
    }

    override fun doPostSetup() {
        AdviceWith.adviceWith(context, "file-mover-kt") {
            it.replaceFromWith("direct:foo")
        }

        context.start()
    }
}

@SpringBootTest(classes=[DemoApplication::class])
@CamelSpringBootTest
@MockEndpoints("file:output")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DummyRouteTests {

    @Autowired lateinit var template: ProducerTemplate
    @EndpointInject("mock:file:output") lateinit var mock: MockEndpoint

    @Test
    @DirtiesContext
    fun foo() {
        mock.expectedBodiesReceived("Hello World")
        template.sendBody("direct:start", null)
        mock.assertIsSatisfied()
    }
}