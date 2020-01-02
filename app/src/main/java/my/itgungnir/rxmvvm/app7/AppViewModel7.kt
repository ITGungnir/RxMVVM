package my.itgungnir.rxmvvm.app7

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

/**
 * Description:
 *
 * Created by ITGungnir on 2020-01-02
 */
class AppViewModel7 constructor() : BaseViewModel<AppState7>(initialState = AppState7()) {

    constructor(initialData: Int) : this() {
        setState {
            copy(
                data = initialData
            )
        }
    }

    class Factory(private val initialData: Int) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = AppViewModel7(initialData = initialData) as T
    }

    fun changeData() {
        var newData: Int = (Math.random() * 1000 + 10).toInt()
        while (newData == getState().data) {
            newData = (Math.random() * 1000 + 10).toInt()
        }
        setState {
            copy(
                data = newData
            )
        }
    }
}
