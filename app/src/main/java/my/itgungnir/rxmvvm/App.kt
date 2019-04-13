package my.itgungnir.rxmvvm

import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import my.itgungnir.rxmvvm.common.redux.MyRedux

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MyRedux.init(this)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }
}