package my.itgungnir.rxmvvm.app4

import my.itgungnir.rxmvvm.core.mvvm.State

data class ChildState(
    val randomNum: Int = 0,
    val error: Throwable? = null
) : State