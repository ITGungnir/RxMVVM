package my.itgungnir.rxmvvm

import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyReducer
import my.itgungnir.rxmvvm.common.redux.middleware.MultipleMiddleware
import my.itgungnir.rxmvvm.common.redux.middleware.PlusMiddleware
import my.itgungnir.rxmvvm.core.redux.Redux

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Redux.init(
            this, AppState::class.java, AppState(), MyReducer(), listOf(
                PlusMiddleware(), MultipleMiddleware()
            )
        )

        if (LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }
}