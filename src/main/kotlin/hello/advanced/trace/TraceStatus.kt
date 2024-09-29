package hello.advanced.trace

import org.springframework.stereotype.Component

class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
)