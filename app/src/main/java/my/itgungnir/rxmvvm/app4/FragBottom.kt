package my.itgungnir.rxmvvm.app4

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app4_bottom.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.ext.shareVM
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment

class FragBottom : BaseFragment<AppVM4>() {

    override fun contentView(): Int = R.layout.fragment_app4_bottom

    override fun shouldBindLifecycle(): Boolean = true

    override fun obtainVM(): AppVM4 = shareVM()

    override fun initComponent() {}

    override fun observeVM() {
        // 监听VM中状态的变化
        vm?.randomNumberState?.observe(this, Observer {
            it?.let { num -> randomNum.text = num.toString() }
        })
    }
}