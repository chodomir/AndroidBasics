package com.example.basics

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import kotlin.reflect.KClass

class UIFragment : Fragment() {

    private lateinit var cbFlagNewTask: CheckBox
    private lateinit var cbFlagClearTop: CheckBox
    private lateinit var cbFlagSingleTop: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ui, container, false)

        // get buttons
        val btnStandard: Button = view.findViewById(R.id.btnStandard)
        val btnSingleTop: Button = view.findViewById(R.id.btnSingleTop)
        val btnSingleTask: Button = view.findViewById(R.id.btnSingleTask)
        val btnSingleInstance: Button = view.findViewById(R.id.btnSingleInstance)

        // get checkboxes
        cbFlagNewTask = view.findViewById(R.id.cbFlagNewTask)
        cbFlagClearTop = view.findViewById(R.id.cbFlagClearTop)
        cbFlagSingleTop = view.findViewById(R.id.cbFlagSingleTop)

        // set button click listeners
        btnStandard.setOnClickListener{
            val intent = createIntentForStartActivity(MainActivity::class)
            startActivity(intent)
        }
        btnSingleTop.setOnClickListener {
            val intent = createIntentForStartActivity(SecondaryActivity::class)
            startActivity(intent)
        }
        btnSingleTask.setOnClickListener {
            val intent = createIntentForStartActivity(TertiaryActivity::class)
            startActivity(intent)
        }
        btnSingleInstance.setOnClickListener {
            val intent = createIntentForStartActivity(QuaternaryActivity::class)
            startActivity(intent)
        }

        return view
    }

    private fun createFlag(newTaskChecked: Boolean, clearTopChecked: Boolean,
                           singleTopChecked: Boolean): Int {
        var flag = if (newTaskChecked) Intent.FLAG_ACTIVITY_NEW_TASK else 0
        flag = if (clearTopChecked) flag or Intent.FLAG_ACTIVITY_CLEAR_TOP else flag
        flag = if (singleTopChecked) flag or Intent.FLAG_ACTIVITY_SINGLE_TOP else flag

        return flag
    }

    private fun createIntentForStartActivity(cls: KClass<*>): Intent {
        return Intent(activity, cls.java).apply {
            val flag = createFlag(
                    cbFlagNewTask.isChecked,
                    cbFlagClearTop.isChecked,
                    cbFlagSingleTop.isChecked
            )
            if (flag != 0) addFlags(flag)
        }
    }
}