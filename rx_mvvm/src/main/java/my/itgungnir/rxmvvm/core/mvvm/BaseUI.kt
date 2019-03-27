package my.itgungnir.rxmvvm.core.mvvm

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

abstract class BaseUI : AnkoComponent<LifecycleOwner> {

    abstract override fun createView(ui: AnkoContext<LifecycleOwner>): View
}