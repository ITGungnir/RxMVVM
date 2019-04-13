package my.itgungnir.rxmvvm.core.mvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class LazyFragment : Fragment() {

    private var isViewCreated: Boolean = false

    private var isViewVisible: Boolean = false

    private var isInitialized: Boolean = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isViewVisible = userVisibleHint
        prepareLoad()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initComponent()
        observeVM()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareLoad()
    }

    private fun prepareLoad() {
        if (isViewCreated && isViewVisible && !isInitialized) {
            onLazyLoad()
            isInitialized = true
        }
    }

    abstract fun layoutId(): Int

    abstract fun initComponent()

    abstract fun onLazyLoad()

    abstract fun observeVM()
}