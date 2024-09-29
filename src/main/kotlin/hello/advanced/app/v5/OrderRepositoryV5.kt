package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class OrderRepositoryV5(private val trace: LogTrace) {

    private val template = TraceTemplate(trace)

    fun save(itemId: String) {

        template.execute("OrderRepository.save()", object :TraceCallback<Unit>{
            override fun call() {
                // 저장 로직
                if (itemId == "ex") {
                    throw IllegalStateException("예외 발생!")
                }
                Thread.sleep(1000)
            }
        })

    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}