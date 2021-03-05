package com.example.basics.d10CustomDialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.basics.R

class D10CustomDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d10_custom_dialog)

    }

    override fun onStart() {
        super.onStart()

        val dlg = MyDialog(this)
        dlg.setTitle("Something different")
        dlg.show()
    }
}