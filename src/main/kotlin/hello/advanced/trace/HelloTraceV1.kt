package hello.advanced.trace

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HelloTraceV1 {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.id,
                addSpace(COMPLETE_PREFIX, traceId.level), status.message, resultTimeMs)
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.id,
                addSpace(EX_PREFIX, traceId.level), status.message, resultTimeMs, e.toString())
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        return buildString {
            repeat(level) { i ->
                append(if (i == level - 1) "|$prefix" else "| ")
            }
        }
    }
}