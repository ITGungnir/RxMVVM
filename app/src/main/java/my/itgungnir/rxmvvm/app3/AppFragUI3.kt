package my.itgungnir.rxmvvm.app3

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.view.Gravity
import android.view.View
import my.itgungnir.rxmvvm.core.mvvm.BaseUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class AppFragUI3 : BaseUI() {

    private lateinit var uiOwner: AppFragment3

    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<LifecycleOwner>): View = with(ui) {
        uiOwner = ui.owner as AppFragment3
        verticalLayout {
            button {
                text = "点击弹出随机数Toast"
            }.lparams(wrapContent, wrapContent).apply {
                setOnClickListener {
                    uiOwner.generateRandomNumber()
                }
            }
        }.apply {
            gravity = Gravity.CENTER
        }
    }
}