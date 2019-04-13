package my.itgungnir.rxmvvm.app2

import my.itgungnir.rxmvvm.core.mvvm.State

data class AppState2(
    val randomNum: Int = 0,
    val error: Throwable? = null
) : State