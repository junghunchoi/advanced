package hello.advanced.trace.template.code

import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.threadlocal.ThreadLocalServiceTest
import org.slf4j.LoggerFactory

class ContextV1(var strategy: Strategy) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        val startTime = System.currentTimeMillis()
        strategy.call()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime : $resultTime")

    }
}