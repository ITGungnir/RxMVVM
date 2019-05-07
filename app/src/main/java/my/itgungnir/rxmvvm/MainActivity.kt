package my.itgungnir.rxmvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import my.itgungnir.rxmvvm.app1.AppActivity1
import my.itgungnir.rxmvvm.app2.AppActivity2
import my.itgungnir.rxmvvm.app3.AppActivity3
import my.itgungnir.rxmvvm.app4.AppActivity4
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyRedux
import my.itgungnir.rxmvvm.common.redux.action.ChangeNum

class MainActivity : AppCompatActivity() {

    private var number = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            MyRedux.instance.dispatch(ChangeNum(number), true)
            println("------>>${MyRedux.instance.currState()?.result}")
        }

        button2.setOnClickListener {
            startActivity(Intent(this, AppActivity1::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, AppActivity2::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, AppActivity3::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this, AppActivity4::class.java))
        }

        observeVM()
    }

    @SuppressLint("SetTextI18n")
    private fun observeVM() {
        MyRedux.instance.pick(AppState::result).observe(this, Observer {
            it?.a?.let { num ->
                if (number > 1) {
                    reduxText.text = "($number + 1) * 2 = $num"
                }
                number++
            }
        })
    }
}