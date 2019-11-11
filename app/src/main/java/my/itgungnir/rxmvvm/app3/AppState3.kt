package my.itgungnir.rxmvvm.app3

import my.itgungnir.rxmvvm.core.mvvm.State

data class AppState3(
    val randomNum: Int = 0,
    val error: Throwable? = null
) : State