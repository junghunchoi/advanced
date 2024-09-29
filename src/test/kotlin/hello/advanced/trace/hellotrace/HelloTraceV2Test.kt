package hello.advanced.trace.hellotrace

import hello.advanced.trace.HelloTraceV2
import org.junit.jupiter.api.Test

class HelloTraceV2Test {
    @Test
    fun begin_end() {
        val trace = HelloTraceV2()
        val status = trace.begin("hello2")
        val status2 = trace.beginSync(status.traceId,"hello2")
        trace.end(status)
        trace.end(status2)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV2()
        val status = trace.begin("hello")
        val status2 = trace.beginSync(status.traceId,"hello2")
        trace.exception(status2, IllegalStateException())
        trace.exception(status, IllegalStateException())
    }
}