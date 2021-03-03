package com.example.basics.dialogs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.example.basics.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

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

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val tvColoredText: TextView = findViewById(R.id.tvColoredText)
        tvColoredText.text = "Hold to change color"
        tvColoredText.setTextColor(Color.BLACK)
        tvColoredText.setOnLongClickListener {
            tvColoredText.setTextColor(Color.MAGENTA)

            // snackbar time
            Snackbar.make(
                findViewById(R.id.clContainer),
                R.string.done,
                Snackbar.LENGTH_LONG
            ).setAction(R.string.cancel, MyUndoListener(tvColoredText))
                .show()

            return@setOnLongClickListener true
        }

        // Bottomsheet behavior configuration
        val bottomSheetBehavior = BottomSheetBehavior.from(findViewById<LinearLayout>(R.id.linearLayout))
        bottomSheetBehavior.peekHeight = 400
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED;
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