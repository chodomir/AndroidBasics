package com.example.basics.dialogs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.example.basics.R
import com.example.basics.activities.UIFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class BasicDialogsActivity : AppCompatActivity(),
        BasicDialogFragment.BasicDialogListener {
    companion object {
        val TAG: String = "BasicDialogsExample"
    }

    class MyUndoListener(var tvText: TextView) : View.OnClickListener {
        override fun onClick(v: View?) {
            tvText.setTextColor(Color.BLACK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_dialogs)

        // Get Views
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // Bind burger menu to onClick callback
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        // Bind nav item to onClick callback
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = !menuItem.isChecked
            // Open a new fragment
            when (menuItem.itemId) {
                R.id.item1 ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvContent, BottomSheetFragment::class.java, null)
                        .commit()
                R.id.item2 ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvContent, UIFragment::class.java, Bundle())
                        .commit()
                else -> Log.d(TAG, "Kliknuo si nesto")
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onStart() {
        super.onStart()

        val basicdf = BasicDialogFragment()
        basicdf.show(supportFragmentManager, BasicDialogFragment.TAG)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d(TAG, "Positive button clicked (host activity)")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.d(TAG, "Negative button clicked (host activity)")
    }
}