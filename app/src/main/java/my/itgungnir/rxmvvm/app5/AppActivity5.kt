package my.itgungnir.rxmvvm.app5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_app5.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
class AppActivity5 : AppCompatActivity() {

    private lateinit var listAdapter: AppListAdapter5

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel5::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app5)
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initData()
        observeVM()
    }

    private fun initViews() {
        // SwipeRefreshLayout
        refreshLayout.setOnRefreshListener {
            initData()
        }
        // RecyclerView
        list.apply {
            layoutManager = LinearLayoutManager(this@AppActivity5, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@AppActivity5, DividerItemDecoration.VERTICAL))
            listAdapter = AppListAdapter5(listOf())
            adapter = listAdapter
        }
    }

    private fun initData() {
        viewModel.refreshData()
    }

    private fun observeVM() {
        // Refreshing state
        viewModel.pick(AppState5::refreshing).observe(this, Observer {
            refreshLayout.isRefreshing = it.a
        })
        // DataList state
        viewModel.pick(AppState5::dataList).observe(this, Observer {
            listAdapter.refresh(it.a)
        })
        // Error state
        viewModel.pick(AppState5::error).observe(this, Observer {
            it.a?.message?.let { msg ->
                Snackbar.make(list, msg, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}