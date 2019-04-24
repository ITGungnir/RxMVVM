package my.itgungnir.rxmvvm.app2

import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app2_bottom.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

class FragBottom : BaseFragment() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = activity!!,
            viewModelClass = AppViewModel2::class.java
        )
    }

    override fun layoutId(): Int = R.layout.fragment_app2_bottom

    override fun initComponent() {}

    override fun observeVM() {

        viewModel.pick(AppState2::randomNum)
            .observe(this, Observer { num ->
                num?.a?.let {
                    randomNum.text = it.toString()
                }
            })

        viewModel.pick(AppState2::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
    }
}