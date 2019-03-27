package my.itgungnir.rxmvvm.core.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

abstract class BaseActivity<T : BaseVM> : AppCompatActivity() {

    protected val vm by lazy { obtainVM() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (val cv = contentView()) {
            is Int -> setContentView(cv)
            else -> (cv as BaseUI).setContentView(this)
        }

        vm?.let { lifecycle.addObserver(it) }

        initComponent()
        observeVM()
    }

    fun dispatchError(e: Throwable) {
        e.message?.let { toast(it) }
    }

    abstract fun contentView(): Any

    abstract fun obtainVM(): T?

    abstract fun initComponent()

    abstract fun observeVM()
}