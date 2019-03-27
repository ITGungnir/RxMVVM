package my.itgungnir.rxmvvm.app2

import android.arch.lifecycle.Observer
import my.itgungnir.rxmvvm.core.ext.createVM
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity
import my.itgungnir.rxmvvm.core.mvvm.BaseUI
import org.jetbrains.anko.toast

/**
 * MVVM + Activity + Anko
 */
class AppActivity2 : BaseActivity<AppVM2>() {

    override fun contentView(): BaseUI = AppUI2()

    override fun obtainVM(): AppVM2 = createVM()

    override fun initComponent() {}

    fun generateRandomNumber() {
        vm?.generateRandomNumber()
    }

    override fun observeVM() {
        // 监听VM中状态的变化
        vm?.randomNumberState?.observe(this, Observer {
            it?.let { num -> toast(num.toString()) }
        })
    }
}