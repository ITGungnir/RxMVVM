package my.itgungnir.rxmvvm.app4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_app4.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

class AppActivity4 : AppCompatActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel4::class.java
        )
    }

    private val pageCount = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app4)

        initComponent()
        observeVM()
    }

    private fun initComponent() {

        viewPager.apply {
            offscreenPageLimit = pageCount
            adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int): Fragment =
                    FragChild.newInstance(position)

                override fun getCount(): Int = pageCount
            }
        }
    }

    private fun observeVM() {

        viewModel.pick(AppState4::newLog)
            .observe(this, Observer { newLog ->
                newLog?.a?.let {
                    log.append("$it\n")
                }
            })
    }
}