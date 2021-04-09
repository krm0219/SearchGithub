package com.kurly.task.searchgithub.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kurly.task.searchgithub.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}