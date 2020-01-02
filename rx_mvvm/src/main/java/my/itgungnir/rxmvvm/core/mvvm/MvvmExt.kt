package my.itgungnir.rxmvvm.core.mvvm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

fun <VM : BaseViewModel<S>, S : State> buildActivityViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<VM>,
    factory: ViewModelProvider.Factory? = null
) = ViewModelProviders.of(activity, factory).get(viewModelClass)

fun <VM : BaseViewModel<S>, S : State> buildFragmentViewModel(
    fragment: Fragment,
    viewModelClass: Class<VM>,
    factory: ViewModelProvider.Factory? = null
) = ViewModelProviders.of(fragment, factory).get(viewModelClass)
