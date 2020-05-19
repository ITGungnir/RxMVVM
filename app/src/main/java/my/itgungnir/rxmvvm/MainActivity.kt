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
import my.itgungnir.rxmvvm.app7.AppActivity7
import my.itgungnir.rxmvvm.redux.AsyncReduxActivity
import my.itgungnir.rxmvvm.redux.BackPressureReduxActivity
import my.itgungnir.rxmvvm.redux.NonPersistReduxActivity
import my.itgungnir.rxmvvm.redux.PersistReduxActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redux_non_persist.setOnClickListener {
            startActivity(Intent(this, NonPersistReduxActivity::class.java))
        }

        redux_persist.setOnClickListener {
            startActivity(Intent(this, PersistReduxActivity::class.java))
        }

        redux_back_pressure.setOnClickListener {
            startActivity(Intent(this, BackPressureReduxActivity::class.java))
        }

        redux_async.setOnClickListener {
            startActivity(Intent(this, AsyncReduxActivity::class.java))
        }

        mvvm01.setOnClickListener {
            startActivity(Intent(this, AppActivity1::class.java))
        }

        mvvm02.setOnClickListener {
            startActivity(Intent(this, AppActivity2::class.java))
        }

        mvvm03.setOnClickListener {
            startActivity(Intent(this, AppActivity3::class.java))
        }

        mvvm04.setOnClickListener {
            startActivity(Intent(this, AppActivity4::class.java))
        }

        mvvm05.setOnClickListener {
            startActivity(Intent(this, AppActivity5::class.java))
        }

        mvvm06.setOnClickListener {
            startActivity(Intent(this, AppActivity6::class.java))
        }

        mvvm07.setOnClickListener {
            startActivity(Intent(this, AppActivity7::class.java))
        }
    }
}