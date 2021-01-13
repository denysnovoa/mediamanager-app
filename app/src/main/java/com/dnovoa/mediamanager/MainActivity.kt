package com.dnovoa.mediamanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnovoa.deluge.ui.DelugeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DelugeFragment.newInstance())
                    .commitNow()
        }
    }
}