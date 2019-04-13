package my.itgungnir.rxmvvm.core.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity

fun <VM : BaseViewModel<S>, S : State> buildActivityViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<VM>
) = ViewModelProviders.of(activity).get(viewModelClass)

fun <VM : BaseViewModel<S>, S : State> buildFragmentViewModel(
    fragment: Fragment,
    viewModelClass: Class<VM>
) = ViewModelProviders.of(fragment).get(viewModelClass)