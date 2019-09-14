package my.itgungnir.rxmvvm.app3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app3_top.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildFragmentViewModel

class FragTop : Fragment() {

    private val viewModel by lazy {
        buildFragmentViewModel(
            fragment = this,
            viewModelClass = AppViewModel3::class.java
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_app3_top, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()
        observeVM()
    }

    private fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    private fun observeVM() {

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