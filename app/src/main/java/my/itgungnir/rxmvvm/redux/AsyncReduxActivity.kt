package my.itgungnir.rxmvvm.redux

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_redux_async.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyRedux
import my.itgungnir.rxmvvm.common.redux.action.Reset
import my.itgungnir.rxmvvm.common.redux.action.ToastEvent

/**
 * Description:
 *
 * @author ITGungnir
 * @date 2020/5/19
 */
class AsyncReduxActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redux_async)

        currentUserName.text = "当前用户名：${MyRedux.instance.currState.username}"

        syncToast.setOnClickListener {
            MyRedux.instance.dispatch(ToastEvent, listOf())
        }

        asyncToast.setOnClickListener {
            Observable.just(1)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    MyRedux.instance.dispatch(ToastEvent, listOf())
                }
        }

        disposable = MyRedux.instance.pick(AppState::toastEvent).subscribe {
            it.a?.let {
                Toast.makeText(this, "消费ToastEvent事件", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        MyRedux.instance.dispatch(Reset, listOf())
        disposable?.takeIf { !it.isDisposed }?.dispose()
        super.onDestroy()
    }
}
