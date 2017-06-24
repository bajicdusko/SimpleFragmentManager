package com.bajicdusko.kotlinfragmentmanager

import android.support.annotation.StringRes

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
interface FragmentChannel {
    fun setToolbarTitle(@StringRes titleId: Int)
}