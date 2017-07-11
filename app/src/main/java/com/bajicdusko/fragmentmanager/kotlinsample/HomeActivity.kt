package com.bajicdusko.fragmentmanager.kotlinsample

import android.content.Intent
import android.support.v7.widget.Toolbar
import com.bajicdusko.fragmentmanager.R
import com.bajicdusko.kotlinfragmentmanager.SFMActivity

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
class HomeActivity : SFMActivity(), ExampleFragmentChannel {

    val toolbar by lazy { findViewById(R.id.activity_home_toolbar) as Toolbar }

    override fun getFrameLayoutContainerId(): Int = R.id.activity_home_fragment_container

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        simpleFragmentManager.enableLogs(true)
        simpleFragmentManager.addFragment(FirstFragment.newInstance())
    }

    override fun openSecondFragment() {
        simpleFragmentManager.replaceFragment(SecondFragment.newInstance())
    }

    override fun setToolbarTitle(titleId: Int) {
        supportActionBar?.title = getString(titleId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        simpleFragmentManager.onActivityResult(requestCode, resultCode, data)
    }
}