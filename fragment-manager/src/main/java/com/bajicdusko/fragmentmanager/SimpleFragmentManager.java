package com.bajicdusko.fragmentmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

/**
 * Created by Dusko Bajic on 24.06.17.
 * GitHub @bajicdusko
 */

public class SimpleFragmentManager {

    private static final String KEY_TAGS = "key_tags";
    private final FragmentManager fragmentManager;
    private final int fragmentContainerId;
    private FragmentTagStack fragmentTagStack = new FragmentTagStack();

    public SimpleFragmentManager(FragmentManager fragmentManager, int fragmentContainerId) {

        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }

    public void addFragment(IFragment fragment) {
        fragmentTagStack.push(fragment.getFragmentName());
        fragmentManager.beginTransaction()
                .add(fragmentContainerId, (Fragment) fragment, fragment.getFragmentName())
                .addToBackStack(fragment.getFragmentName())
                .commit();
    }

    public void replaceFragment(IFragment fragment) {
        fragmentTagStack.push(fragment.getFragmentName());
        fragmentManager.beginTransaction()
                .replace(fragmentContainerId, (Fragment) fragment, fragment.getFragmentName())
                .addToBackStack(fragment.getFragmentName())
                .commit();
    }

    public boolean onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            popUp();
            IFragment currentFragment = getCurrentFragment();
            if (currentFragment != null) {
                currentFragment.setTitle();
                return true;
            }
        }

        return false;
    }

    public void popUp() {
        fragmentManager.popBackStackImmediate();
        fragmentTagStack.pop();
    }

    public void popUpAll() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTagStack.popUpAll();
    }

    public IFragment getCurrentFragment() {
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragmentTagStack.getActiveTag());
        if (fragmentByTag != null) {
            return ((IFragment) fragmentByTag);
        } else {
            return null;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data, null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, String targetTag) {
        String tag = targetTag;
        if (TextUtils.isEmpty(tag)) {
            tag = fragmentTagStack.getActiveTag();
        }

        if (!TextUtils.isEmpty(tag)) {
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
            if (fragmentByTag != null) {
                fragmentByTag.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void dispose() {
        IFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            currentFragment.dispose();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.putParcelable(KEY_TAGS, fragmentTagStack);
        }
    }

    public void onRestoreInstanceState(Bundle outState) {
        if (outState != null) {
            fragmentTagStack = outState.getParcelable(KEY_TAGS);
        } else {
            fragmentTagStack = new FragmentTagStack();
        }
    }

    public void enableLogs(boolean logsEnabled) {
        fragmentTagStack.setShowLogs(logsEnabled);
    }
}
