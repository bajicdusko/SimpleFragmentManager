package com.bajicdusko.kotlinfragmentmanager

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
class SimpleFragmentManager(private val fragmentManager: FragmentManager, private val fragmentContainerId: Int) {

    private val KEY_TAGS = "key_tags"
    private var fragmentTagStack: FragmentTagStack = FragmentTagStack()

    fun addFragment(fragment: IFragment) {
        fragmentTagStack.push(fragment.getFragmentName())
        fragmentManager.beginTransaction()
                .add(fragmentContainerId, fragment as Fragment, fragment.getFragmentName())
                .addToBackStack(fragment.getFragmentName())
                .commit()
    }

    fun replaceFragment(fragment: IFragment) {
        fragmentTagStack.push(fragment.getFragmentName())
        fragmentManager.beginTransaction()
                .replace(fragmentContainerId, fragment as Fragment, fragment.getFragmentName())
                .addToBackStack(fragment.getFragmentName())
                .commit()
    }

    /**
     * When [Activity.onBackPressed] is called it is a good practice to override it and to call this method.
     * This method won't allow removal of last fragment on the stack.
     *
     * @return TRUE if fragment is poppedUp aka method is consumed, or FALSE if there is only one Fragment
     * on the stack.
     */
    fun onBackPressed(): Boolean {
        if (fragmentManager.backStackEntryCount > 1) {
            popUp()
            var currentFragment = getCurrentFragment()
            currentFragment?.setTitle()
            return true
        } else {
            return false
        }
    }

    fun popUp() {
        fragmentManager.popBackStackImmediate()
        fragmentTagStack.pop()
    }

    fun popUpAll() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentTagStack.popUpAll()
    }

    fun getCurrentFragment(): IFragment? = fragmentManager.findFragmentByTag(fragmentTagStack.activeTag) as IFragment?

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onActivityResult(requestCode, resultCode, data, null)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, targetFragmentTag: String?) {
        var tag = targetFragmentTag
        if (tag.isNullOrEmpty()) {
            tag = fragmentTagStack.activeTag
        }

        var fragmentByTag = fragmentManager.findFragmentByTag(tag)
        fragmentByTag?.onActivityResult(requestCode, resultCode, data)
    }

    fun dispose() = getCurrentFragment()?.dispose()

    fun onSaveInstanceState(state: Bundle?) {
        state?.putParcelable(KEY_TAGS, fragmentTagStack)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            fragmentTagStack = savedInstanceState.getParcelable(KEY_TAGS)
        } else {
            fragmentTagStack = FragmentTagStack()
        }
    }

    fun enableLogs(logsEnabled: Boolean) {
        fragmentTagStack.setShowLogs(logsEnabled)
    }
}