package my.itgungnir.rxmvvm.common.redux.middleware

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.action.ChangeNum
import my.itgungnir.rxmvvm.common.redux.action.MultiTwo
import my.itgungnir.rxmvvm.common.redux.action.NullAction
import my.itgungnir.rxmvvm.core.redux.Action
import my.itgungnir.rxmvvm.core.redux.Middleware

class PlusMiddleware : Middleware<AppState> {

    override fun apply(state: AppState, action: Action, dispatch: (Action) -> Unit): Action = when (action) {
        is ChangeNum -> {
            Observable.just(action.newNum + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dispatch(MultiTwo(it))
                }
            NullAction
        }
        else ->
            action
    }
}