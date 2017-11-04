package com.bajicdusko.fragmentmanager.kotlinsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bajicdusko.fragmentmanager.R
import com.bajicdusko.kotlinfragmentmanager.SFMFragment


/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
class SecondFragment : SFMFragment<ExampleFragmentChannel>() {
    private val FRAGMENT_NAME: String = "Second"

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_second, container,
      false)

    override fun onResume() {
        super.onResume()
        setTitle()
    }

    override fun getFragmentName(): String = FRAGMENT_NAME

    override fun setTitle(): Unit? = fragmentChannel?.setToolbarTitle(R.string.second)

    override fun dispose() {
        //Clear the resources
    }

    companion object {
        fun newInstance(): SecondFragment = SecondFragment()
    }
}