package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.template.code.ContextV1
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory


class Contextv1Test {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun strategyv1() {
        val strategy = StrategyLogic1()
        val contextv1 = ContextV1(strategy)
        contextv1.execute()
    }

    @Test
    fun strategyv2() {
        val strategyLogic = object : Strategy {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        }

    }

}