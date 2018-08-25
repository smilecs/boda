package com.smile.boda

import android.content.Context

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private lateinit var appInstance: Application
        fun getsInstance(): Application = appInstance

        val appContext: Context
            get() = appInstance.applicationContext
    }
}