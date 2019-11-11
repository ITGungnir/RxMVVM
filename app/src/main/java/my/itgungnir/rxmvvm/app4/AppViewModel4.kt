package my.itgungnir.rxmvvm.app4

import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

class AppViewModel4 : BaseViewModel<AppState4>(initialState = AppState4()) {

    fun appendLog(newLog: String) {
        setState {
            copy(
                newLog = newLog
            )
        }
    }
}