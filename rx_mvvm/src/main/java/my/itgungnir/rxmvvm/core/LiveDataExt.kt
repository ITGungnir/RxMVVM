package my.itgungnir.rxmvvm.core

import androidx.lifecycle.LiveData

fun <T, R> LiveData<T>.map(func: (T) -> (R)): LiveData<R> =
    LiveDataUtil.map(this, func)

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> =
    LiveDataUtil.distinctUntilChanged(this)