package com.example.basics.d11

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basics.R

private const val CUSTOM_ACTION = "action.skuza.skuza.videh.te"
private val receiver = MyBroadcastReceiver()

class D11MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d11_main)

        val intentFilter = IntentFilter(CUSTOM_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()

        Intent().also { intent ->
            intent.action = CUSTOM_ACTION
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}