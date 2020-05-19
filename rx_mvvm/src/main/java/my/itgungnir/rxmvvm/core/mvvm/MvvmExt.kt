package my.itgungnir.rxmvvm.core.mvvm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

fun <VM : BaseViewModel<S>, S : State> buildActivityViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<VM>,
    factory: ViewModelProvider.Factory? = null
) = if (null == factory) ViewModelProvider(activity)[viewModelClass]
else ViewModelProvider(activity, factory)[viewModelClass]

fun <VM : BaseViewModel<S>, S : State> buildFragmentViewModel(
    fragment: Fragment,
    viewModelClass: Class<VM>,
    factory: ViewModelProvider.Factory? = null
) = if (null == factory) ViewModelProvider(fragment)[viewModelClass]
else ViewModelProvider(fragment, factory)[viewModelClass]
