package my.itgungnir.rxmvvm.redux

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_redux_persist.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyRedux
import my.itgungnir.rxmvvm.common.redux.action.Login
import my.itgungnir.rxmvvm.common.redux.action.Logout
import my.itgungnir.rxmvvm.common.redux.action.Reset
import my.itgungnir.rxmvvm.common.redux.middleware.LoginMiddleware
import java.util.concurrent.TimeUnit

class PersistReduxActivity : AppCompatActivity() {

    private var disposable1: Disposable? = null
    private var disposable2: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redux_persist)

        initComponents()
        observeVM()
    }

    @SuppressLint("CheckResult")
    private fun initComponents() {

        RxView.clicks(login)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                val usernameStr = userNameInput.editableText.toString().trim()
                val passwordStr = passwordInput.editableText.toString().trim()
                MyRedux.instance.dispatch(Login(usernameStr, passwordStr), listOf(LoginMiddleware()))
            }

        RxView.clicks(logout)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                MyRedux.instance.dispatch(Logout)
            }
    }

    @SuppressLint("CheckResult")
    private fun observeVM() {

        Observable.combineLatest(
            arrayOf(
                RxTextView.textChanges(userNameInput),
                RxTextView.textChanges(passwordInput)
            )
        ) {
            it.all { item -> item.toString().trim().isNotEmpty() }
        }.subscribe {
            login.isEnabled = it
        }

        disposable1 = MyRedux.instance.pick(AppState::username).subscribe {
            if (it.a.isBlank()) {
                loginLayer.visibility = View.VISIBLE
                userInfoLayer.visibility = View.GONE
            } else {
                loginLayer.visibility = View.GONE
                userInfoLayer.visibility = View.VISIBLE
                userName.text = it.a
            }
        }

        disposable2 = MyRedux.instance.pick(AppState::loginFail).subscribe {
            it.a?.let {
                Toast.makeText(this, "登录失败，请重试", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        MyRedux.instance.dispatch(Reset, listOf())
        disposable1?.takeIf { !it.isDisposed }?.dispose()
        disposable2?.takeIf { !it.isDisposed }?.dispose()
        super.onDestroy()
    }
}