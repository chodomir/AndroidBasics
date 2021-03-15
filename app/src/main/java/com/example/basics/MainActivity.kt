package com.example.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.basics.activities.PrimaryActivity
import com.example.basics.d10CustomDialog.D10CustomDialogActivity
import com.example.basics.d11.D11MainActivity
import com.example.basics.d12.D12MainActivity
import com.example.basics.d13.D13MainActivity
import com.example.basics.d15.D15MainActivity
import com.example.basics.d16.D16MainActivity
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
        val btnExample4: Button = findViewById(R.id.btnExample4)
        val btnExample5: Button = findViewById(R.id.btnExample5)
        val btnExample6: Button = findViewById(R.id.btnExample6)
        val btnExample7: Button = findViewById(R.id.btnExample7)
        val btnExample8: Button = findViewById(R.id.btnExample8)
        val btnExample9: Button = findViewById(R.id.btnExample9)

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
        btnExample4.setOnClickListener {
            val intent = Intent(this, D10CustomDialogActivity::class.java)
            startActivity(intent)
        }
        btnExample5.setOnClickListener {
            val intent = Intent(this, D11MainActivity::class.java)
            startActivity(intent)
        }
        btnExample6.setOnClickListener {
            val intent = Intent(this, D12MainActivity::class.java)
            startActivity(intent)
        }
        btnExample7.setOnClickListener {
            val intent = Intent(this, D13MainActivity::class.java)
            startActivity(intent)
        }
        btnExample8.setOnClickListener {
            val intent = Intent(this, D15MainActivity::class.java)
            startActivity(intent)
        }
        btnExample9.setOnClickListener {
            val intent = Intent(this, D16MainActivity::class.java)
            startActivity(intent)
        }

    }
}