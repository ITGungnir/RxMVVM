package my.itgungnir.rxmvvm.core.mvvm

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> = LiveDataUtil.distinctUntilChanged(this)