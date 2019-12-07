package my.itgungnir.rxmvvm.common.http

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline fun <reified T> Single<Response<T>>.handleResult() = compose { upsteam ->
    upsteam.flatMap {
        when (it.errorCode) {
            0 ->
                Single.just(it.data ?: T::class.java.newInstance())
            else ->
                Single.error(HttpException(it.errorMsg))
        }
    }
}!!

fun <T> Single<T>.io2Main(): Single<T> {
    val transformer: SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    return compose(transformer)
}