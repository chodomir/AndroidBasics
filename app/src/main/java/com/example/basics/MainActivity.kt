package com.example.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.basics.activities.PrimaryActivity
import com.example.basics.d9ActionBar.D9ActionBarActivity
import com.example.basics.dialogs.BasicDialogsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get buttons
        val btnExample1: Button = findViewById(R.id.btnExample1)
        val btnExample2: Button = findViewById(R.id.btnExample2)
        val btnExample3: Button = findViewById(R.id.btnExample3)

        // set click listeners
        btnExample1.setOnClickListener {
            val intent = Intent(this, PrimaryActivity::class.java)
            startActivity(intent)
        }
        btnExample2.setOnClickListener {
            val intent = Intent(this, BasicDialogsActivity::class.java)
            startActivity(intent)
        }
        btnExample3.setOnClickListener {
            val intent = Intent(this, D9ActionBarActivity::class.java)
            startActivity(intent)
        }
    }
}