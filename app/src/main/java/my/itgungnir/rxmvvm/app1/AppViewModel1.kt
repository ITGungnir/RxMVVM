package my.itgungnir.rxmvvm.app1

import android.annotation.SuppressLint
import io.reactivex.Single
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

class AppViewModel1 : BaseViewModel<AppState1>(initialState = AppState1()) {

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
                        error = Throwable(message = "生成随机数失败！")
                    )
                }
            })
    }
}