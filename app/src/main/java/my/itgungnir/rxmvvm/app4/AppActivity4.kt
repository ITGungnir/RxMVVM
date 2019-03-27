package my.itgungnir.rxmvvm.app4

import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.ext.createVM
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity

/**
 * MVVM Activity + 多个Fragment + shareVM
 */
class AppActivity4 : BaseActivity<AppVM4>() {

    override fun contentView(): Int = R.layout.activity_app4

    override fun obtainVM(): AppVM4 = createVM()

    override fun initComponent() {

        supportFragmentManager.beginTransaction()
            .add(R.id.top, FragTop())
            .add(R.id.bottom, FragBottom())
            .commit()
    }

    override fun observeVM() {}
}