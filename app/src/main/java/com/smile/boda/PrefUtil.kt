package com.smile.boda

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.Delegates

object PrefUtil {
    private const val token = "token"
    var props: SharedPreferences by Delegates.notNull()

    init {
        props = App.getsInstance().getSharedPreferences("boda_key", Context.MODE_PRIVATE)
    }

    fun getToken() = props.getString(token, null)

    fun setToken(tokenString: String) {
        props.edit().putString(token, tokenString).apply()
    }

}