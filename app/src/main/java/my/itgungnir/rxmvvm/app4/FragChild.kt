package my.itgungnir.rxmvvm.app4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app4_child.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel
import my.itgungnir.rxmvvm.core.mvvm.buildFragmentViewModel

class FragChild : Fragment() {

    private var isInitialized = false

    private val innerViewModel by lazy {
        buildFragmentViewModel(
            fragment = this,
            viewModelClass = ChildViewModel::class.java
        )
    }

    private val outerViewModel by lazy {
        buildActivityViewModel(
            activity = activity!!,
            viewModelClass = AppViewModel4::class.java
        )
    }

    private var flag = 0

    companion object {
        fun newInstance(flag: Int) = FragChild().apply { this.flag = flag }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_app4_child, container, false)

    override fun onResume() {
        super.onResume()
        if (!isInitialized) {
            initComponent()
            observeVM()
            isInitialized = true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initComponent() {
        title.text = "App4 Fragment $flag"

        button.setOnClickListener {
            innerViewModel.generateRandomNumber()
        }

        outerViewModel.appendLog("Fragment$flag 懒加载成功！")
    }

    private fun observeVM() {

        innerViewModel.pick(ChildState::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        innerViewModel.pick(ChildState::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
    }
}