package hello.advanced.app.v1

import hello.advanced.trace.HelloTraceV1
import hello.advanced.trace.TraceStatus
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV1(
    private val orderRepository: OrderRepositoryV1,
    private val trace: HelloTraceV1) {

    fun orderItem(itemId: String) {
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
