package hello.advanced.trace.callback

interface TraceCallback<T> {
    fun call(): T

}