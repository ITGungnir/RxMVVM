package my.itgungnir.rxmvvm.core.ext

import android.arch.lifecycle.ViewModelProviders
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import my.itgungnir.rxmvvm.core.mvvm.BaseVM

inline fun <reified T : BaseVM> BaseActivity<T>.createVM(): T = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : BaseVM> BaseFragment<T>.createVM(): T = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : BaseVM> BaseFragment<T>.shareVM(): T = ViewModelProviders.of(activity!!).get(T::class.java)