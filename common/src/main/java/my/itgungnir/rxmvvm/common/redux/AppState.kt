package my.itgungnir.rxmvvm.common.redux

import my.itgungnir.rxmvvm.core.redux.DoPersist

data class AppState(
    val result: Int = 0,
    val loginFail: Unit? = null,
    @DoPersist val username: String = ""
)