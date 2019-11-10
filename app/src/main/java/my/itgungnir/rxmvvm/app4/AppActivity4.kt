package my.itgungnir.rxmvvm.app4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
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

    override fun createViews(savedInstanceState: Bundle?) {
        viewPager.apply {
            offscreenPageLimit = pageCount
            adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int): Fragment = FragChild.newInstance(position)

                override fun getCount(): Int = pageCount
            }
        }

        viewModel.pick(AppState4::newLog)
            .observe(this, Observer { newLog ->
                newLog?.a?.let {
                    log.append("$it\n")
                }
            })
    }
}