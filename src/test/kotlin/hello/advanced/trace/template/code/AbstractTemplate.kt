package hello.advanced.trace.template.code

import org.slf4j.LoggerFactory

public abstract class AbstractTemplate {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        call()

        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }

    protected abstract fun call()
}