package my.itgungnir.rxmvvm.common.redux

import my.itgungnir.rxmvvm.common.redux.action.GetResult
import my.itgungnir.rxmvvm.common.redux.action.LoginFail
import my.itgungnir.rxmvvm.common.redux.action.LoginSuccess
import my.itgungnir.rxmvvm.common.redux.action.Logout
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Reducer

class MyReducer : Reducer<AppState> {

    override fun reduce(state: AppState, action: Action): AppState? = when (action) {
        is GetResult ->
            state.copy(result = action.result)
        is LoginSuccess ->
            state.copy(username = action.username)
        is LoginFail ->
            state.copy(loginFail = Unit)
        is Logout ->
            state.copy(username = "")
        else ->
            null
    }
}