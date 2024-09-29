package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV2
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.template.TemplateMethodTest
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV2Test {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    @Test
    fun strategyV1() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
    }

    @Test
    fun strategyV2() {
        val context = ContextV2()
        context.execute { log.info("비즈니스 로직 실행") }
    }
}