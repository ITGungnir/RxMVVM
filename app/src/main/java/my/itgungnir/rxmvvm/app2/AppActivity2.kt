package my.itgungnir.rxmvvm.app2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import my.itgungnir.rxmvvm.R

/**
 * MVVM Activity + 多个Fragment + shareVM
 */
class AppActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app2)

        supportFragmentManager.beginTransaction()
            .add(R.id.top, FragTop())
            .add(R.id.bottom, FragBottom())
            .commit()
    }
}