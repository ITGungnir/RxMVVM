package my.itgungnir.rxmvvm.app3

import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity

class AppActivity3 : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_app3

    override fun initComponent() {

        supportFragmentManager.beginTransaction()
            .add(R.id.top, FragTop())
            .add(R.id.bottom, FragBottom())
            .commit()
    }

    override fun observeVM() {
    }
}