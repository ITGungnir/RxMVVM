package my.itgungnir.rxmvvm.app2

import kotlinx.android.synthetic.main.fragment_app2_top.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

class FragTop : BaseFragment() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = activity!!,
            viewModelClass = AppViewModel2::class.java
        )
    }

    override fun layoutId(): Int = R.layout.fragment_app2_top

    override fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    override fun observeVM() {}
}