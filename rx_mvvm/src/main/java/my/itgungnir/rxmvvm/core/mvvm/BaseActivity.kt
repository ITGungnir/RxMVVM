package my.itgungnir.rxmvvm.core.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())

        initComponent()
        observeVM()
    }

    abstract fun layoutId(): Int

    abstract fun initComponent()

    abstract fun observeVM()
}