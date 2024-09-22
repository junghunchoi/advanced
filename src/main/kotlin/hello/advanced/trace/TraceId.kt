package hello.advanced.trace

import java.util.*

class TraceId(id: String? = null, level: Int = 0) {
    val id: String
    var level: Int

    init {
        this.id = id ?: createId()
        this.level = level
    }

    private fun createId(): String {
        return UUID.randomUUID().toString().substring(0, 8)
    }

    fun createNextId(): TraceId {
        return TraceId(this.id, this.level + 1)
    }

    fun createPreviousId(): TraceId {
        return TraceId(this.id, this.level - 1)
    }

    fun isFirstLevel(): Boolean {
        return this.level == 0;
    }
}