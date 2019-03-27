package my.itgungnir.rxmvvm.core.mvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext

abstract class BaseFragment<T : BaseVM> : Fragment() {

    protected val vm by lazy { obtainVM() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        when (val cv = contentView()) {
            is Int -> inflater.inflate(cv, container, false)
            else -> (cv as BaseUI).createView(AnkoContext.create(context!!, this))
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (shouldBindLifecycle()) {
            vm?.let { lifecycle.addObserver(it) }
        }

        initComponent()
        observeVM()
    }

    protected fun dispatchError(e: Throwable) {
        (activity as BaseActivity<*>).dispatchError(e)
    }

    abstract fun contentView(): Any

    abstract fun shouldBindLifecycle(): Boolean

    abstract fun obtainVM(): T?

    abstract fun initComponent()

    abstract fun observeVM()
}