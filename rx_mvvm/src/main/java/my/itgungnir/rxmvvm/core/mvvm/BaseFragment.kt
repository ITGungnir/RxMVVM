package my.itgungnir.rxmvvm.core.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId(), container, false)
        createViews(view, savedInstanceState)
        return view
    }

    abstract fun layoutId(): Int

    abstract fun createViews(view: View, savedInstanceState: Bundle?)
}