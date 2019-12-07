package my.itgungnir.rxmvvm.app5

import my.itgungnir.rxmvvm.core.mvvm.State

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
data class AppState5(
    val refreshing: Boolean = false,
    val dataList: List<String> = listOf(),
    val error: Throwable? = null
) : State
