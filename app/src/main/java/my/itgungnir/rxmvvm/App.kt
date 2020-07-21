package my.itgungnir.rxmvvm

import androidx.multidex.MultiDexApplication
import my.itgungnir.rxmvvm.common.redux.MyRedux

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MyRedux.init(this)
    }
}