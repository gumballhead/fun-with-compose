package com.example.cleankt

sealed class Result<T> {
    @Throws
    abstract fun unwrap(): T
    abstract fun <R> map(op: (T) -> R): Result<R>
    abstract fun <R> flatMap(op: (T) -> Result<R>): Result<R>

    companion object {
        fun <T> wrap(expr: () -> T) = try {
            Success(expr())
        } catch (error: Throwable) {
            Failure(error)
        }
    }
}

class Success<T>(val data: T) : Result<T>() {
    override fun <R> map(op: (T) -> R) = wrap { op(data) }
    override fun <R> flatMap(op: (T) -> Result<R>) = op(data)
    override fun unwrap() = data
}

class Failure<T>(val error: Throwable) : Result<T>() {
    override fun <R> map(op: (T) -> R) = Failure<R>(error)
    override fun <R> flatMap(op: (T) -> Result<R>) = Failure<R>(error)
    override fun unwrap() = throw error
}

sealed class AsyncState<Model>
class Loading<Model>: AsyncState<Model>()
class Complete<Model>(val result: Result<Model>): AsyncState<Model>()
