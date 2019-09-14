package my.itgungnir.rxmvvm.redux

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_redux_persist.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.common.redux.AppState
import my.itgungnir.rxmvvm.common.redux.MyRedux
import my.itgungnir.rxmvvm.common.redux.action.Login
import my.itgungnir.rxmvvm.common.redux.action.Logout
import my.itgungnir.rxmvvm.common.redux.middleware.LoginMiddleware

class PersistReduxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redux_persist)

        initComponents()
        observeVM()
    }

    @SuppressLint("CheckResult")
    private fun initComponents() {

        RxView.clicks(login)
            .subscribe {
                val usernameStr = userNameInput.editableText.toString().trim()
                val passwordStr = passwordInput.editableText.toString().trim()
                MyRedux.instance.dispatch(Login(usernameStr, passwordStr), listOf(LoginMiddleware()))
            }

        RxView.clicks(logout)
            .subscribe {
                MyRedux.instance.dispatch(Logout, listOf())
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

        MyRedux.instance.pick(AppState::username).observe(this, Observer {
            if (it.a.isBlank()) {
                loginLayer.visibility = View.VISIBLE
                userInfoLayer.visibility = View.GONE
            } else {
                loginLayer.visibility = View.GONE
                userInfoLayer.visibility = View.VISIBLE
                userName.text = it.a
            }
        })

        MyRedux.instance.pick(AppState::loginFail).observe(this, Observer {
            it.a?.let {
                Toast.makeText(this, "登录失败，请重试", Toast.LENGTH_SHORT).show()
            }
        })
    }
}