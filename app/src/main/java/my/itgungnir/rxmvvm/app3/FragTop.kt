package my.itgungnir.rxmvvm.app3

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app3_top.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import my.itgungnir.rxmvvm.core.mvvm.buildFragmentViewModel
import org.jetbrains.anko.support.v4.toast

class FragTop : BaseFragment() {

    private val viewModel by lazy {
        buildFragmentViewModel(
            fragment = this,
            viewModelClass = AppViewModel3::class.java
        )
    }

    override fun layoutId(): Int = R.layout.fragment_app3_top

    override fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    override fun observeVM() {

        viewModel.pick(AppState3::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        viewModel.pick(AppState3::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    toast(it)
                }
            })
    }
}