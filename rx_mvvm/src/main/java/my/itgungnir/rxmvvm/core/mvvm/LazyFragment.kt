package my.itgungnir.rxmvvm.core.mvvm

abstract class LazyFragment : BaseFragment() {

    private var initialized = false

    override fun onResume() {
        super.onResume()
        if (!initialized) {
            onLazyLoad()
            initialized = true
        }
    }

    abstract fun onLazyLoad()
}
