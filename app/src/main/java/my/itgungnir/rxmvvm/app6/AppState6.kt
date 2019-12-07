package my.itgungnir.rxmvvm.app6

import my.itgungnir.rxmvvm.core.mvvm.State

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
data class AppState6(
    val progressing: Boolean = true,
    val value: Int? = null
) : State