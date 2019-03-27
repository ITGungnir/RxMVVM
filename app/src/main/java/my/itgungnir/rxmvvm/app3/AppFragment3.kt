package my.itgungnir.rxmvvm.app3

import android.arch.lifecycle.Observer
import my.itgungnir.rxmvvm.core.ext.createVM
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import org.jetbrains.anko.support.v4.toast

class AppFragment3 : BaseFragment<AppFragVM3>() {

    override fun contentView(): AppFragUI3 = AppFragUI3()

    override fun shouldBindLifecycle(): Boolean = true

    override fun obtainVM(): AppFragVM3 = createVM()

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