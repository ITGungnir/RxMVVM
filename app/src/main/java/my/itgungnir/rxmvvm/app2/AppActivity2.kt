package my.itgungnir.rxmvvm.app2

import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity

/**
 * MVVM Activity + 多个Fragment + shareVM
 */
class AppActivity2 : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_app2

    override fun initComponent() {

        supportFragmentManager.beginTransaction()
            .add(R.id.top, FragTop())
            .add(R.id.bottom, FragBottom())
            .commit()
    }

    override fun observeVM() {}
}