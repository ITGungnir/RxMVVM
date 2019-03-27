package my.itgungnir.rxmvvm.app1

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_app1.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.ext.createVM
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity
import org.jetbrains.anko.toast

/**
 * MVVM Activity
 */
class AppActivity1 : BaseActivity<AppVM1>() {

    override fun contentView(): Int = R.layout.activity_app1

    override fun obtainVM(): AppVM1 = createVM()

    override fun initComponent() {
        button.setOnClickListener {
            vm?.generateRandomNumber()
        }
    }

    override fun observeVM() {
        // 监听VM中状态的变化
        vm?.randomNumberState?.observe(this, Observer {
            it?.let { num -> toast(num.toString()) }
        })
    }
}