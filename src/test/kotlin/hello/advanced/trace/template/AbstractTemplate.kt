package hello.advanced.trace.template

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(private val trace: LogTrace) {

    fun execute(message: String): T {
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            // 로직 호출
            val result = call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            status?.let { trace.exception(it, e) }
            throw e
        }
    }

    protected abstract fun call(): T

}