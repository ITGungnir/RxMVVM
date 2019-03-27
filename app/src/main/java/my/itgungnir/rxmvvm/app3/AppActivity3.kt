package my.itgungnir.rxmvvm.app3

import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.app1.AppVM1
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity
import my.itgungnir.rxmvvm.core.mvvm.BaseVM

/**
 * MVVM Activity + Fragment + Anko
 */
class AppActivity3 : BaseActivity<BaseVM>() {

    override fun contentView(): Int = R.layout.activity_app3

    override fun obtainVM(): AppVM1? = null

    override fun initComponent() {

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, AppFragment3())
            .commit()
    }

    override fun observeVM() {}
}