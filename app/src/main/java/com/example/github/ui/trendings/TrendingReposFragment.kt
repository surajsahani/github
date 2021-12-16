package com.example.github.ui.trendings


import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.github.KEY_DATA
import com.example.github.ui.Action
import com.example.github.ui.ActionManager
import com.example.github.ui.ActionType
import com.example.github.ui.BaseFragment


class TrendingReposFragment : BaseFragment() {

    companion object {
        fun newInstance(): TrendingReposFragment = TrendingReposFragment()
    }

    @Inject
    lateinit var viewModelFactory: TrendingReposViewModelFactory
    @Inject
    lateinit var actionManager: ActionManager

    private val trendingReposVM: TrendingReposViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[TrendingReposViewModel::class.java]
    }

    private var loadMore = true
    private var refresh = true
    private var reposAdapter: ReposRecyclerAdapter? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingReposVM.reposLiveData.observe(this, Observer<MutableList<Repo>>(::onChanged))
        trendingReposVM.errorsLiveData.observe(this, Observer<Error>(::onChanged))
    }

    override fun onViewReady(view: View, savedInstanceState: Bundle?) {
        if (reposAdapter == null) {
            reposAdapter = ReposRecyclerAdapter(mutableListOf())
            reposAdapter?.onRepoItemClickListener = ::onRepoClicked
            loadRepos(true)
        }
        recycler_repos.adapter = reposAdapter
        recycler_repos.layoutManager = LinearLayoutManager(context)
        swipe_repos.setOnRefreshListener {
            loadRepos(true)
        }
        recycler_repos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView?.layoutManager
                layoutManager?.let {
                    val last = (it as LinearLayoutManager).findLastVisibleItemPosition()
                    val count = recyclerView.adapter.itemCount
                    if (loadMore && !swipe_repos.isRefreshing && last == count - 1) {
                        loadRepos(false)
                    }
                }
            }
        })
    }

    private fun loadRepos(reset: Boolean) {
        swipe_repos.isRefreshing = true
        if (reset)
            refresh = true
        trendingReposVM.loadTrendingRepos(reset)
    }

    private fun onChanged(data: Any?) {
        swipe_repos.isRefreshing = false
        when (data) {
            is Error -> {
                when (data) {
                    Error.SUCCESS -> {
                    }
                    Error.NO_MORE_DATA -> {
                        loadMore = false
                    }
                }
            }
            is MutableList<*> -> {
                if (refresh) {
                    reposAdapter!!.repos.clear()
                    refresh = false
                }
                reposAdapter!!.repos.addAll(data as Collection<Repo>)
                recycler_repos.adapter.notifyDataSetChanged()
            }
        }
    }

    private fun onRepoClicked(repo: Repo) {
        val data = Bundle()
        data.putParcelable(KEY_DATA, repo)
        actionManager.fire(Action(ActionType.ACTION_REPO, data))
    }

    override fun getLayoutId(): Int = R.layout.fragment_trending_repos
}