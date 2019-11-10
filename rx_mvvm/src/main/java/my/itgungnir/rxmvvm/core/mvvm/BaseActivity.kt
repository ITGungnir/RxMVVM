package my.itgungnir.rxmvvm.core.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        createViews(savedInstanceState)
    }

    abstract fun layoutId(): Int

    abstract fun createViews(savedInstanceState: Bundle?)
}