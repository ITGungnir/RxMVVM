package my.itgungnir.rxmvvm.app4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_app4_child.*
import kotlinx.android.synthetic.main.fragment_app4_child.view.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.LazyFragment
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel
import my.itgungnir.rxmvvm.core.mvvm.buildFragmentViewModel

class FragChild : LazyFragment() {

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

    override fun layoutId(): Int = R.layout.fragment_app4_child

    @SuppressLint("SetTextI18n")
    override fun createViews(view: View, savedInstanceState: Bundle?) {
        view.title.text = "App4 Fragment $flag"

        view.button.setOnClickListener {
            innerViewModel.generateRandomNumber()
        }

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

    override fun onLazyLoad() {
        outerViewModel.appendLog("Fragment$flag 懒加载成功！")
    }
}