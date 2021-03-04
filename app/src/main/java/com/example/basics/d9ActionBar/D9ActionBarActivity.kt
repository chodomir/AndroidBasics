package com.example.basics.d9ActionBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.example.basics.R

class D9ActionBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Disable native ActionBar first!!
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
        // Then call super onCreate()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_d9_action_bar)
        // Now that default ActionBar is disabled, this is safe to call
        setSupportActionBar(findViewById(R.id.d9_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.d9_toolbar_menu, menu)
        return true
    }
}