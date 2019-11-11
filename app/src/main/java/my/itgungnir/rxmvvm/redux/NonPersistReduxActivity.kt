package my.itgungnir.rxmvvm.redux

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_redux_non_persist.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyRedux
import my.itgungnir.rxmvvm.common.redux.action.ChangeNum
import my.itgungnir.rxmvvm.common.redux.middleware.MultipleMiddleware
import my.itgungnir.rxmvvm.common.redux.middleware.PlusMiddleware

class NonPersistReduxActivity : AppCompatActivity() {

    private var currNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redux_non_persist)

        initComponents()
        observeVM()
    }

    private fun initComponents() {
        btnExchange.setOnClickListener {
            MyRedux.instance.dispatch(ChangeNum(currNum), listOf(PlusMiddleware(), MultipleMiddleware()))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeVM() {
        MyRedux.instance.pick(AppState::result).observe(this, Observer {
            if (currNum == 1) {
                tvResult.text = "($currNum + 1) * 2 = 4"
            } else {
                tvResult.text = "($currNum + 1) * 2 = ${it.a}"
            }
            currNum++
        })
    }
}