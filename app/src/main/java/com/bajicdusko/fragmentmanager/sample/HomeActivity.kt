package com.bajicdusko.fragmentmanager.sample

import com.bajicdusko.fragmentmanager.SFMActivity

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
class HomeActivity : SFMActivity(), ExampleFragmentChannel {

    val toolbar by lazy { findViewById(R.id.activity_home_toolbar) as android.support.v7.widget.Toolbar }

    override fun getFrameLayoutContainerId(): Int = R.id.activity_home_fragment_container

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        simpleFragmentManager.enableLogs(true)
        simpleFragmentManager.addFragment(FirstFragment.Companion.newInstance())
    }

    override fun openSecondFragment() {
        simpleFragmentManager.replaceFragment(SecondFragment.Companion.newInstance())
    }

    override fun setToolbarTitle(titleId: Int) {
        supportActionBar?.title = getString(titleId)
    }
}