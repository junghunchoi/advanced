package hello.advanced.app.v1

import hello.advanced.trace.HelloTraceV1
import hello.advanced.trace.TraceStatus
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderControllerV1(
    private val orderService: OrderServiceV1,
    private val helloTraceV1: HelloTraceV1
) {


    @GetMapping("/v1/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = helloTraceV1.begin("OrderController.request()")
            orderService.orderItem(itemId)
            helloTraceV1.end(status)
            return "ok";
        } catch (e: Exception) {
            status?.let { helloTraceV1.exception(it, e) }
            throw e
        }
    }
}