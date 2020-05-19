package my.itgungnir.rxmvvm.common.redux

import my.itgungnir.rxmvvm.common.redux.action.*
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Reducer
import java.util.*

class MyReducer : Reducer<AppState> {

    override fun reduce(state: AppState, action: Action): AppState? = when (action) {
        is GetResult ->
            state.copy(result = action.result)
        is LoginSuccess ->
            state.copy(username = action.username, loginFail = null)
        is LoginFail ->
            state.copy(loginFail = Unit)
        is Logout ->
            state.copy(username = "")
        is ToastEvent ->
            state.copy(toastEvent = UUID.randomUUID().toString())
        is Reset ->
            state.copy(loginFail = null, toastEvent = null)
        is AddNum ->
            state.copy(backPressureNum = state.backPressureNum + 1)
        else ->
            null
    }
}