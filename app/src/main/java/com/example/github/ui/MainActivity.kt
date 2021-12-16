package com.example.github.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.github.R
import com.example.github.ui.repo.RepoDetailsFragment
import com.example.github.ui.trendings.TrendingReposFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var actionManager: ActionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        actionManager.onActionListener = ::fireAction

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            actionManager.fire(Action(ActionType.ACTION_TRENDING_REPOS))
        }
    }

    private fun fireAction(action: Action) {
        when (action.type) {
            ActionType.UNKNOWN -> Log.w(javaClass.simpleName, "Unknown Action Fired!")
            ActionType.ACTION_TRENDING_REPOS -> transition(TrendingReposFragment.newInstance(), replace = false)
            ActionType.ACTION_REPO -> transition(RepoDetailsFragment.newInstance(action.data))
        }
    }

    private fun transition(fragment: BaseFragment, keepCurrent: Boolean = true,
                           replace: Boolean = true) {
        if (!keepCurrent && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }

        val transaction = supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

        if (replace) {
            transaction
                    .replace(R.id.container, fragment)
                    .addToBackStack(fragment.javaClass.simpleName)
        } else {
            transaction.add(R.id.container, fragment)
        }

        transaction.commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragmentDispatchingAndroidInjector
}
