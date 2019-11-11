package my.itgungnir.rxmvvm.app2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app2_bottom.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

class FragBottom : Fragment() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = activity!!,
            viewModelClass = AppViewModel2::class.java
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_app2_bottom, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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