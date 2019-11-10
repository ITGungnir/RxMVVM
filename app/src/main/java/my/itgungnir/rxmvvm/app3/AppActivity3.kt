package my.itgungnir.rxmvvm.app3

import android.os.Bundle
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity

class AppActivity3 : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_app3

    override fun createViews(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.top, FragTop())
            .add(R.id.bottom, FragBottom())
            .commit()
    }
}