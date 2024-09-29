package hello.advanced.app.v3

import hello.advanced.trace.HelloTraceV2
import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV3(
    private val orderRepository: OrderRepositoryV3,
    private val trace: LogTrace
) {

    fun orderItem( itemId: String) {
        var status: TraceStatus? =null
        try {
            status = trace.begin("OrderService.orderItem()")
            orderRepository.save(itemId)
            trace.end(status)
        } catch (e: Exception) {
            status?.let { trace.exception(it, e) }
            throw e
        }
    }
}
