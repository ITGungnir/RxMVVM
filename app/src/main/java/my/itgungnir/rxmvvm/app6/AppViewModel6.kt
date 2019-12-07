package my.itgungnir.rxmvvm.app6

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel
import java.util.concurrent.TimeUnit

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
class AppViewModel6 : BaseViewModel<AppState6>(initialState = AppState6()) {

    fun loadData() =
        Observable.timer(10, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                setState {
                    copy(
                        progressing = true
                    )
                }
            }
            .doFinally {
                setState {
                    copy(
                        progressing = false
                    )
                }
            }
            .subscribe {
                setState {
                    copy(
                        value = (Math.random() * 100).toInt()
                    )
                }
            }!!
}
