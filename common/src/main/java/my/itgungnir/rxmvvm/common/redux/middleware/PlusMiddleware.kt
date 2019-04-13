package my.itgungnir.rxmvvm.common.redux.middleware

import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.action.ChangeNum
import my.itgungnir.rxmvvm.common.redux.action.MultiTwo
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Middleware

class PlusMiddleware : Middleware<AppState> {

    override fun apply(state: AppState, action: Action): Action = when (action) {
        is ChangeNum ->
            MultiTwo(action.newNum + 1)
        else ->
            action
    }
}