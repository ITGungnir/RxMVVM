package my.itgungnir.rxmvvm.app4

import kotlinx.android.synthetic.main.fragment_app4_top.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.ext.shareVM
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment

class FragTop : BaseFragment<AppVM4>() {

    override fun contentView(): Int = R.layout.fragment_app4_top

    override fun shouldBindLifecycle(): Boolean = true

    override fun obtainVM(): AppVM4 = shareVM()

    override fun initComponent() {
        button.setOnClickListener {
            vm?.generateRandomNumber()
        }
    }

    override fun observeVM() {
    }
}