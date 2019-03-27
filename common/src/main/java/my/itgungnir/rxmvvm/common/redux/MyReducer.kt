package my.itgungnir.rxmvvm.common.redux

import my.itgungnir.rxmvvm.common.redux.action.GetResult
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Reducer

class MyReducer : Reducer {

    override fun reduce(state: Any, action: Action): Any {

        if (state !is AppState) {
            return AppState()
        }

        return when (action) {
            is GetResult -> state.copy(result = action.result)
            else -> state
        }
    }
}