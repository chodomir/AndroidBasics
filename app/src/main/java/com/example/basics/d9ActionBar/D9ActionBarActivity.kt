package com.example.basics.d9ActionBar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.example.basics.R

class D9ActionBarActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Disable native ActionBar first!!
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
        // Then call super onCreate()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d9_action_bar)

        // Initialize tvResult
        tvResult = findViewById(R.id.d9_tvResult)
        // Now that default ActionBar is disabled, this is safe to call
        setSupportActionBar(findViewById(R.id.d9_toolbar))
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private var times = 0
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.d9_toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.d9_toolbar_search_action)
        val searchView = searchItem?.actionView as SearchView

        // Define the query text listener
        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                tvResult.text = "You searched for: $query"
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tvResult.text = "... $newText"
                return true
            }
        }
        // Apply defined listener
        searchView.setOnQueryTextListener(queryTextListener)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.d9_toolbar_action_1 -> {
                tvResult.text = getText(R.string.title_1)
                true
            }
            R.id.d9_toolbar_action_2 -> {
                tvResult.text = getText(R.string.title_2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}