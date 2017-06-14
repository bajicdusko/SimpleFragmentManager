package com.bajicdusko.fragmentmanager

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
interface IFragment {
    fun getFragmentName(): String

    fun setTitle(): Unit?

    fun dispose()
}