package com.smile.boda.ui.util

import com.smile.boda.App
import com.smile.boda.R

object Util {
    fun getTextFromAny(name: Any?, key: String = "$", resId: Int = R.string.lang) =
            if (name is ArrayList<*>) {
                val nameMap = name as ArrayList<Map<String, String>>
                nameMap.find {
                    it.containsValue(App.getsInstance().getString(resId))
                }
            } else {
                name as Map<String, String>
            }?.get(key)
}