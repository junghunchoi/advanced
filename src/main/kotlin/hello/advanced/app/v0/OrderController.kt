package hello.advanced.app.v0

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderController(private val orderService: OrderService) {

    @GetMapping("/v0/request")
    fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok";
    }
}