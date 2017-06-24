package com.bajicdusko.kotlinfragmentmanager

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import java.lang.StringBuilder
import java.util.*

/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
class FragmentTagStack : Parcelable {

    private var showLogs: Boolean = false

    var tags: MutableList<String>
        private set

    var activeTag: String? = null
        private set

    constructor(){
        tags = LinkedList()
    }

    constructor(p: Parcel){
        tags = LinkedList()
        p.readStringList(tags)
        activeTag = p.readString()
    }

    /**
     * Enabling or disabling fragment stack logs. Logs are disabled by default.

     * @param showLogs
     */
    fun setShowLogs(showLogs: Boolean) {
        this.showLogs = showLogs
    }

    /**
     * Pushing new tag to stack and setting is as [FragmentTagStack.activeTag]

     * @param tag
     */
    fun push(tag: String) {
        tags.add(tag)
        activeTag = tag
        logStack()
    }

    /**
     * Popping tag from the stack and setting the tag below as new [FragmentTagStack.activeTag]
     *
     *
     * Example:
     *
     *
     * Stack [3, 2, 1, 0] (3 is last added tag). [FragmentTagStack.activeTag] have value 3
     *
     *
     * Now we're popping the last added tag
     *
     *
     * Stack [2, 1, 0] [FragmentTagStack.activeTag] have value 2

     * @return Popped up value. From the example above, returned value will be 3. In case of empty stack, null will be returned.
     */
    fun pop(): String? {
        val tag = peek()
        if (!TextUtils.isEmpty(tag)) {
            tags.removeAt(tags.size - 1)
        }

        activeTag = peek()
        logStack()
        return tag
    }

    /**
     * Peeking to the top of the stack, without data modification.

     * @return Value on top of the stack. If stack is empty, returned value will be null.
     */
    fun peek(): String? {
        if (tags.size > 0) {
            return tags[tags.size - 1]
        } else {
            return null
        }
    }

    private fun logStack() {
        if (showLogs) {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Active tag: $activeTag\nStack:\n")
            for (i in tags.size - 1 downTo 0) {
                stringBuilder.append("[ ${tags[i]} ]\n")
            }
            Log.d(TAG, stringBuilder.toString())
        }
    }

    /**
     * Clears the stack.
     */
    fun popUpAll() {
        tags.clear()
    }

    companion object {

        private val TAG = "FragmentTagStack"
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeStringList(tags)
        dest?.writeString(activeTag)
    }

    override fun describeContents(): Int = 0
}