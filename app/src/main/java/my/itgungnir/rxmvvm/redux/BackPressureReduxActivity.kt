package my.itgungnir.rxmvvm.redux

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_redux_back_pressure.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyRedux
import my.itgungnir.rxmvvm.common.redux.action.AddNum
import java.util.concurrent.TimeUnit

/**
 * Description:
 *
 * @author ITGungnir
 * @date 2020/5/19
 */
class BackPressureReduxActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redux_back_pressure)

        initViews()
        observeVM()
    }

    @SuppressLint("CheckResult")
    private fun initViews() {
        RxView.clicks(btnAdd)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                for (i in 0..1000) {
                    MyRedux.instance.dispatch(AddNum, listOf())
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun observeVM() {
        disposable = MyRedux.instance.pick(AppState::backPressureNum).subscribe {
            println("---------->>${it.a}")
            tvResult.text = it.a.toString()
        }
    }

    override fun onDestroy() {
        disposable?.takeIf { !it.isDisposed }?.dispose()
        super.onDestroy()
    }
}