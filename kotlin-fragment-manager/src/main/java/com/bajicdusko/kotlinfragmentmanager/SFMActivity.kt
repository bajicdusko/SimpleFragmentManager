package com.bajicdusko.kotlinfragmentmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Dusko Bajic on 14.06.17.
 * GitHub @bajicdusko
 */
abstract class SFMActivity : AppCompatActivity() {

    /**
     * Override this method and return the resource id of the fragment container layout.
     */
    abstract fun getFrameLayoutContainerId(): Int

    val simpleFragmentManager by lazy { SimpleFragmentManager(supportFragmentManager, getFrameLayoutContainerId()) }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        simpleFragmentManager.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        simpleFragmentManager.onRestoreInstanceState(savedInstanceState)
    }

    protected fun onSFMBackPressed() {
        if (!simpleFragmentManager.onBackPressed()) {
            finish()
        }
    }

    override fun onBackPressed() {
        onSFMBackPressed()
    }
}