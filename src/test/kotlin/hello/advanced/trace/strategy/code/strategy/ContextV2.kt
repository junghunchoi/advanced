package hello.advanced.trace.strategy.code.strategy

import hello.advanced.trace.template.TemplateMethodTest
import org.slf4j.LoggerFactory

/**
 * 전략을 파라미터로 전달받기 위한 방식
 */
class ContextV2 {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        strategy.call()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime : $resultTime")

    }
}