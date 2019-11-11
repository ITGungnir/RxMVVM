package my.itgungnir.rxmvvm.app3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import my.itgungnir.rxmvvm.R

class AppActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app3)

        supportFragmentManager.beginTransaction()
            .add(R.id.top, FragTop())
            .add(R.id.bottom, FragBottom())
            .commit()
    }
}