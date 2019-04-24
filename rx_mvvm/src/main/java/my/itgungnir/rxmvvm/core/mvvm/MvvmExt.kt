package my.itgungnir.rxmvvm.core.mvvm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders

fun <VM : BaseViewModel<S>, S : State> buildActivityViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<VM>
) = ViewModelProviders.of(activity).get(viewModelClass)

fun <VM : BaseViewModel<S>, S : State> buildFragmentViewModel(
    fragment: Fragment,
    viewModelClass: Class<VM>
) = ViewModelProviders.of(fragment).get(viewModelClass)