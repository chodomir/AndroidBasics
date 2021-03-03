package com.example.basics.dialogs

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.basics.R
import com.google.android.material.snackbar.Snackbar

class BottomSheetFragment : Fragment(R.layout.fragment_bottomsheet) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottomsheet, container, false)
        // Get Views
        val clContainer: CoordinatorLayout = view.findViewById(R.id.clBottomSheetContainer)
        val tvColoredText: TextView = view.findViewById(R.id.tvColoredText)

        // bind hold event on textView
        tvColoredText.setOnLongClickListener {
            // change text color
            tvColoredText.setTextColor(Color.MAGENTA)
            // Show snackbar to user with "undo" button
            Snackbar.make(clContainer, R.string.done, Snackbar.LENGTH_LONG)
                    .setAction(R.string.cancel, BasicDialogsActivity.MyUndoListener(tvColoredText))
                    .show()
            true
        }

        return view
    }
}