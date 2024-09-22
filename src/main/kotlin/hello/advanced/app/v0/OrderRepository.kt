package hello.advanced.app.v0

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class OrderRepository {

    fun save(itemId: String) {
        if (itemId.equals("ex")) {
            throw IllegalArgumentException("예외 발생!")
        }
        sleep(1000)
    }

    private fun sleep(mills: Long) {
        try {
            Thread.sleep(mills)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}