package hello.advanced.trace.logtrace

import hello.advanced.trace.HelloTraceV2
import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

class FieldLogTrace : LogTrace {
    private val log = LoggerFactory.getLogger(FieldLogTrace::class.java)

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }

    private var traceIdHolder: TraceId? = null // traceId 동기화, 동시성 이슈 발생

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
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
        releaseTraceId()
    }

    private fun syncTraceId() {
        traceIdHolder = traceIdHolder?.createNextId() ?: TraceId()
    }

    private fun releaseTraceId() {
        if (traceIdHolder?.isFirstLevel() == true) {
            traceIdHolder = null // destroy
        } else {
            traceIdHolder = traceIdHolder?.createPreviousId()
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        return (0 until level).joinToString("") { i ->
            if (i == level - 1) "|$prefix" else "| "
        }
    }
}