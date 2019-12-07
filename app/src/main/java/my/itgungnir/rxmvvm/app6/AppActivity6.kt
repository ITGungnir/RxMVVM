package my.itgungnir.rxmvvm.app6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_app6.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
class AppActivity6 : AppCompatActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel6::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app6)
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initEvents()
        observeVM()
    }

    private fun initViews() {
        viewModel.loadData()
    }

    private fun initEvents() {
        refreshResult.setOnClickListener {
            viewModel.loadData()
        }
    }

    private fun observeVM() {
        // Progressing state
        viewModel.pick(AppState6::progressing).observe(this, Observer {
            when (it.a) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                    contentLayout.visibility = View.GONE
                }
                else -> {
                    progressBar.visibility = View.GONE
                    contentLayout.visibility = View.VISIBLE
                }
            }
        })
        // Value state
        viewModel.pick(AppState6::value).observe(this, Observer {
            it?.a?.let { value ->
                resultValue.text = value.toString()
            }
        })
    }
}