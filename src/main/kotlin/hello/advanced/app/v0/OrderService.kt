package hello.advanced.app.v0

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderService(private val orderRepository: OrderRepository ) {

    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}