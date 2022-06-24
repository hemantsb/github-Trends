package bit.hemant.git.trends.feature_repo.domain.util



sealed class AsyncResult<out R> {

    data class Success<out T>(val data: T) : AsyncResult<T>()
    data class SuccessEmpty<out T>(val details: String) : AsyncResult<T>()
    data class ErrorMessage(val errorMessage: String) : AsyncResult<Nothing>()
    object Loading : AsyncResult<Nothing>()


    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is SuccessEmpty -> "Success[data=$details]"
            is ErrorMessage -> "Error[exception=$errorMessage]"
            Loading -> "Loading"
        }
    }
}

val AsyncResult<*>.succeeded
    get() = this is AsyncResult.Success && data != null

val AsyncResult<*>.failed
    get() = this is AsyncResult.ErrorMessage

fun <T> AsyncResult<T>.successOr(fallback: T): T {
    return (this as? AsyncResult.Success<T>)?.data ?: fallback
}

val <T> AsyncResult<T>.data: T?
    get() = (this as? AsyncResult.Success)?.data
