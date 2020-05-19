package my.itgungnir.rxmvvm.common.redux

import my.itgungnir.rxmvvm.core.redux.DoPersist

data class AppState(
    val result: Int = 0,
    val loginFail: Unit? = null,
    val backPressureNum: Long = 0L,
    val toastEvent: String? = null,
    @DoPersist val username: String = ""
)