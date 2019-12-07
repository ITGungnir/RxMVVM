package my.itgungnir.rxmvvm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import my.itgungnir.rxmvvm.app1.AppActivity1
import my.itgungnir.rxmvvm.app2.AppActivity2
import my.itgungnir.rxmvvm.app3.AppActivity3
import my.itgungnir.rxmvvm.app4.AppActivity4
import my.itgungnir.rxmvvm.app5.AppActivity5
import my.itgungnir.rxmvvm.app6.AppActivity6
import my.itgungnir.rxmvvm.redux.NonPersistReduxActivity
import my.itgungnir.rxmvvm.redux.PersistReduxActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            startActivity(Intent(this, NonPersistReduxActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, PersistReduxActivity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, AppActivity1::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, AppActivity2::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this, AppActivity3::class.java))
        }

        button6.setOnClickListener {
            startActivity(Intent(this, AppActivity4::class.java))
        }

        button7.setOnClickListener {
            startActivity(Intent(this, AppActivity5::class.java))
        }

        button8.setOnClickListener {
            startActivity(Intent(this, AppActivity6::class.java))
        }
    }
}