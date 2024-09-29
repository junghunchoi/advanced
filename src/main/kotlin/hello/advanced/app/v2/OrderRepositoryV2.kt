package hello.advanced.app.v2

import hello.advanced.trace.HelloTraceV2
import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class OrderRepositoryV2(private val trace: HelloTraceV2) {

    fun save(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = trace.beginSync(traceId,"OrderRepository.save()")
            // 저장 로직
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!")
            }
            sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            status?.let { trace.exception(it, e) }
            throw e
        }
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}