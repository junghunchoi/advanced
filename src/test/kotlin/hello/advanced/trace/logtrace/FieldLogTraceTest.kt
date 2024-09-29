package hello.advanced.trace.logtrace

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FieldLogTraceTest {
    private val trace: FieldLogTrace = FieldLogTrace()

    @Test
    fun begin_end_level2() {
        val status1 = trace.begin("hello1")
        val status2 = trace.begin("hello2")

        trace.end(status2)
        trace.end(status1)
    }
}