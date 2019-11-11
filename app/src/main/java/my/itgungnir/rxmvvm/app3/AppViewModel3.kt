package my.itgungnir.rxmvvm.app3

import android.annotation.SuppressLint
import io.reactivex.Single
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

class AppViewModel3 : BaseViewModel<AppState3>(initialState = AppState3()) {

    @SuppressLint("CheckResult")
    fun generateRandomNumber() {
        Single.just((1..100).random())
            .subscribe({
                setState {
                    copy(
                        randomNum = it,
                        error = null
                    )
                }
            }, {
                setState {
                    copy(
                        error = kotlin.Throwable(message = "生成随机数失败！")
                    )
                }
            })
    }
}