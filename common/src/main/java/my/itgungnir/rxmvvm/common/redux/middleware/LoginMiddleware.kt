package my.itgungnir.rxmvvm.common.redux.middleware

import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.action.Login
import my.itgungnir.rxmvvm.common.redux.action.LoginFail
import my.itgungnir.rxmvvm.common.redux.action.LoginSuccess
import my.itgungnir.rxmvvm.common.redux.action.NullAction
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Middleware

class LoginMiddleware : Middleware<AppState> {

    override fun apply(state: AppState, action: Action, dispatch: (Action) -> Unit): Action =
        when (action) {
            is Login -> {
                if (action.username.isNotBlank() && action.password == "123456") {
                    dispatch(LoginSuccess(action.username))
                } else {
                    dispatch(LoginFail)
                }
                NullAction
            }
            else -> action
        }
}