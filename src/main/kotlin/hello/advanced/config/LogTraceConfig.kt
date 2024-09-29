package hello.advanced.config

import hello.advanced.trace.logtrace.FieldLogTrace
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    public fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }
}