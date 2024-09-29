package hello.advanced.app.v2

import hello.advanced.trace.HelloTraceV2
import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2,
    private val trace: HelloTraceV2
) {

    fun orderItem(traceId: TraceId, itemId: String) {
        var status: TraceStatus? =null
        try {
            status = trace.beginSync(traceId,"OrderService.orderItem()")
            orderRepository.save(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            status?.let { trace.exception(it, e) }
            throw e
        }
    }
}
