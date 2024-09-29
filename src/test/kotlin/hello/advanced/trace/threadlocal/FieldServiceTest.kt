package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

class FieldServiceTest {
    private val log = LoggerFactory.getLogger(FieldServiceTest::class.java)
    private val fieldService: FieldService = FieldService()

    @Test
    fun field() {
        log.info("main start")
        val userA = Runnable { fieldService.logic("userA") }
        val userB = Runnable { fieldService.logic("userB") }

        val threadA = Thread(userA)
        threadA.setName("thread - a")
        val threadB = Thread(userA)
        threadB.setName("thread - B")

        threadA.start()
        sleep(2000) // 동시성 문제 발생 x
        threadB.start()
        sleep(2000) // 메인 스레드 종료
        log.info("main exit")
    }


}