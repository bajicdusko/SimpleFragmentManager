package com.bajicdusko.fragmentmanager.sample

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
class SecondFragment : com.bajicdusko.fragmentmanager.EasyFragmentBase<ExampleFragmentChannel>() {
    private val FRAGMENT_NAME: String = "Second"

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View? = inflater?.inflate(R.layout.fragment_second, container, false)

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
        fun newInstance(): com.bajicdusko.fragmentmanager.sample.SecondFragment = com.bajicdusko.fragmentmanager.sample.SecondFragment()
    }
}