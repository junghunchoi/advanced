package hello.advanced.app.v2

import hello.advanced.trace.HelloTraceV2
import hello.advanced.trace.TraceStatus
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderControllerV2(
    private val orderService: OrderServiceV2,
    private val helloTraceV2: HelloTraceV2
) {


    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = helloTraceV2.begin("OrderController.request()")
            orderService.orderItem(status.traceId, itemId)
            helloTraceV2.end(status)
            return "ok";
        } catch (e: Exception) {
            status?.let { helloTraceV2.exception(it, e) }
            throw e
        }
    }
}