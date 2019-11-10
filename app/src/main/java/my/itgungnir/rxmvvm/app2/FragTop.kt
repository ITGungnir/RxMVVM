package my.itgungnir.rxmvvm.app2

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_app2_top.view.*
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

    override fun createViews(view: View, savedInstanceState: Bundle?) {
        view.button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }
}