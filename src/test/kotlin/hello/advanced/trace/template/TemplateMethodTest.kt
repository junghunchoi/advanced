package hello.advanced.trace.template

import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLogic1
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateMethodTest {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    @Test
    fun templateMethodV0() {

    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        log.info("비즈니스 로직1 실행")

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime : $resultTime")

    }

    @Test
    fun templateMethodv1() {
        val template1 = SubClassLogic1()
        template1.execute()
    }

    @Test
    fun templateMethodv2() {
        val template1 = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        }
        log.info("클래스 이름1={}", template1.javaClass)
        template1.execute()

        val template2 = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직2 실행")  // 비즈니스 로직2로 수정했습니다.
            }
        }
        log.info("클래스 이름2={}", template2.javaClass)
        template2.execute()


    }
}