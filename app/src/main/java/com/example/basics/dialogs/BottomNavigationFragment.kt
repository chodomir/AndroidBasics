package com.example.basics.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basics.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false)

        // get BottomNavigationView
        val bnv: BottomNavigationView = view.findViewById(R.id.bottom_navigation)

        // do something when user clicks an item in BottomNavigationView
        bnv.setOnNavigationItemSelectedListener {
            BasicDialogFragment().show(childFragmentManager, BasicDialogFragment.TAG)
            true
        }

        return view
    }
}