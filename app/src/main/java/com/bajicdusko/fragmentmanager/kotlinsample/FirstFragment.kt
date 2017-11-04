package com.bajicdusko.fragmentmanager.kotlinsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bajicdusko.fragmentmanager.R
import com.bajicdusko.kotlinfragmentmanager.SFMFragment

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
class FirstFragment : SFMFragment<ExampleFragmentChannel>() {
    private val FRAGMENT_NAME: String = "First"

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_first, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    val button: Button = view.findViewById(R.id.fragment_first_bt_open_second)
    button.setOnClickListener { fragmentChannel?.openSecondFragment() }
    }

    override fun onResume() {
        super.onResume()
        setTitle()
    }

    override fun getFragmentName(): String = FRAGMENT_NAME

    override fun setTitle(): Unit? = fragmentChannel?.setToolbarTitle(R.string.first)

    override fun dispose() {
        //Clear the resources
    }

    companion object {
        fun newInstance(): FirstFragment = FirstFragment()
    }
}