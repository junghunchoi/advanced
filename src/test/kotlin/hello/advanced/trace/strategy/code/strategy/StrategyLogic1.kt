package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

class StrategyLogic1: Strategy {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun call() {
        log.info("비즈니스 로직 1 실행")
    }
}