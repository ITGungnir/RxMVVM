package my.itgungnir.rxmvvm.common.redux.middleware

import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.action.GetResult
import my.itgungnir.rxmvvm.common.redux.action.MultiTwo
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Middleware

class MultipleMiddleware : Middleware {

    override fun apply(state: Any, action: Action): Action {

        if (state !is AppState) {
            return action
        }

        return when (action) {
            is MultiTwo -> GetResult(action.number * 2)
            else -> action
        }
    }
}