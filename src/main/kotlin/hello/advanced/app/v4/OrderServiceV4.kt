package hello.advanced.app.v4

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV4(
    private val orderRepository: OrderRepositoryV4,
    private val trace: LogTrace
) {

    fun orderItem(itemId: String) {
        val template = object : AbstractTemplate<Unit>(trace) {
            override fun call() {
                orderRepository.save(itemId)
            }
        }
        template.execute("OrderService.orderItem()")
    }
}
