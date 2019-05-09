package my.itgungnir.rxmvvm.common.redux.middleware

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.action.GetResult
import my.itgungnir.rxmvvm.common.redux.action.MultiTwo
import my.itgungnir.rxmvvm.common.redux.action.NullAction
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Middleware

class MultipleMiddleware : Middleware<AppState> {

    override fun apply(state: AppState, action: Action, dispatch: (Action) -> Unit): Action = when (action) {
        is MultiTwo -> {
            Observable.just(action.number * 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dispatch(GetResult(it))
                }
            NullAction
        }
        else ->
            action
    }
}