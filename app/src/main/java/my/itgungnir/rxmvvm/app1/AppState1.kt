package my.itgungnir.rxmvvm.app1

import my.itgungnir.rxmvvm.core.mvvm.State

data class AppState1(
    val randomNum: Int = 0,
    val error: Throwable? = null
) : State