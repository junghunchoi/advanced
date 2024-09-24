package hello.advanced.trace.hellotrace

import hello.advanced.trace.HelloTraceV1
import org.junit.jupiter.api.Test

class HelloTraceV1Test {
    @Test
    fun begin_end() {
        val trace = HelloTraceV1()
        val status = trace.begin("hello")
        trace.end(status)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV1()
        val status = trace.begin("hello")
        trace.exception(status, IllegalStateException())
    }
}