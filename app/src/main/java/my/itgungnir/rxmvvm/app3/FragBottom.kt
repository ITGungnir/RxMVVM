package my.itgungnir.rxmvvm.app3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app3_bottom.*
import kotlinx.android.synthetic.main.fragment_app3_bottom.view.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import my.itgungnir.rxmvvm.core.mvvm.buildFragmentViewModel

class FragBottom : BaseFragment() {

    private val viewModel by lazy {
        buildFragmentViewModel(
            fragment = this,
            viewModelClass = AppViewModel3::class.java
        )
    }

    override fun layoutId(): Int = R.layout.fragment_app3_bottom

    override fun createViews(view: View, savedInstanceState: Bundle?) {
        view.button.setOnClickListener {
            viewModel.generateRandomNumber()
        }

        viewModel.pick(AppState3::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })
        viewModel.pick(AppState3::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
    }
}