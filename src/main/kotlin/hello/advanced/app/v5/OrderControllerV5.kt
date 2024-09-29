package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class OrderControllerV5(
    private val orderService: OrderServiceV5,
    trace: LogTrace
) {
    private val template: TraceTemplate = TraceTemplate(trace)


    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return template.execute("OrderController.request()", object : TraceCallback<String> {
            override fun call(): String {
                orderService.orderItem(itemId)
                return "ok"
            }
        })
    }
}