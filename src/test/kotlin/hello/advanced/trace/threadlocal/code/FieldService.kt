package hello.advanced.trace.threadlocal.code

import hello.advanced.trace.logtrace.FieldLogTrace
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory

class FieldService {
    private val log = LoggerFactory.getLogger(FieldService::class.java)

    private var nameStore: String = ""

    fun logic(name: String): String {
        log.info("저장 name={} -> nameStore={}", name, nameStore)
        nameStore = name
        sleep(1000)
        log.info("조회 nameStore={}", nameStore)
        return nameStore
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            log.error("Sleep interrupted", e)
        }
    }
}