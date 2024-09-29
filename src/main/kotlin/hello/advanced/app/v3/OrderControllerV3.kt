package hello.advanced.app.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderControllerV3(
    private val orderService: OrderServiceV3,
    private val trace: LogTrace
) {


    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = trace.begin("OrderController.request()")
            orderService.orderItem(itemId)
            trace.end(status)
            return "ok";
        } catch (e: Exception) {
            status?.let { trace.exception(it, e) }
            throw e
        }
    }
}