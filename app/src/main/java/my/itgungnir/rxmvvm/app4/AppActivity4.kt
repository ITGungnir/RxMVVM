package my.itgungnir.rxmvvm.app4

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_app4.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

class AppActivity4 : BaseActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel4::class.java
        )
    }

    private val pageCount = 5

    override fun layoutId(): Int = R.layout.activity_app4

    override fun initComponent() {

        viewPager.apply {
            offscreenPageLimit = pageCount
            adapter = object : FragmentPagerAdapter(supportFragmentManager) {
                override fun getItem(position: Int): Fragment =
                    FragChild.newInstance(position)

                override fun getCount(): Int = pageCount
            }
        }
    }

    override fun observeVM() {

        viewModel.pick(AppState4::newLog)
            .observe(this, Observer { newLog ->
                newLog?.a?.let {
                    log.append("$it\n")
                }
            })
    }
}