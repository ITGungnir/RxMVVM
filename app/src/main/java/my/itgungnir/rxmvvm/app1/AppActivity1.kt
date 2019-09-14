package my.itgungnir.rxmvvm.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_app1.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

/**
 * MVVM Activity
 */
class AppActivity1 : AppCompatActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel1::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app1)

        initComponent()
        observeVM()
    }

    private fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    private fun observeVM() {

        viewModel.pick(AppState1::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        viewModel.pick(AppState1::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            })
    }
}