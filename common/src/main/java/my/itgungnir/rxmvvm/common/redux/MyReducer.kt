package my.itgungnir.rxmvvm.common.redux

import my.itgungnir.rxmvvm.common.redux.action.GetResult
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Reducer

class MyReducer : Reducer<AppState> {

    override fun reduce(state: AppState, action: Action): AppState = when (action) {
        is GetResult ->
            state.copy(result = action.result)
        else ->
            state
    }
}