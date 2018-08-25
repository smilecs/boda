package com.smile.boda.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smile.boda.R
import com.smile.boda.ui.fragment.MainFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, MainFragment())
        }.commit()
    }
}
