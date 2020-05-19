package my.itgungnir.rxmvvm.core.redux

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Flowable适配Redux，支持背压，切换线程
 */
fun <T> Flowable<T>.adaptRedux() = this.onBackpressureLatest()
    .observeOn(AndroidSchedulers.mainThread())
