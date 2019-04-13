package my.itgungnir.rxmvvm.app4

import android.annotation.SuppressLint
import io.reactivex.Single
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

class ChildViewModel : BaseViewModel<ChildState>(initialState = ChildState()) {

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