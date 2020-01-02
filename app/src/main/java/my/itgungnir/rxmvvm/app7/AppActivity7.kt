package my.itgungnir.rxmvvm.app7

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_app7.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

/**
 * Description:
 *
 * Created by ITGungnir on 2020-01-02
 */
class AppActivity7 : AppCompatActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel7::class.java,
            factory = AppViewModel7.Factory(9999)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app7)

        initViews()
        observeVM()
    }

    private fun initViews() {
        change_data.setOnClickListener {
            viewModel.changeData()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeVM() {
        viewModel.pick(AppState7::data).observe(this, Observer {
            data_presenter.text = "DATA: ${it.a}"
        })
    }
}