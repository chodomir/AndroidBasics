package com.example.basics.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.basics.R
import kotlin.reflect.KClass

class UIFragment : Fragment(R.layout.fragment_ui) {
    companion object {
        val TAG = "UIFragment"
        val IS_EDITING_KEY = "isEditing.key"
        val TEXT_KEY = "text.key"
    }

    private lateinit var cbFlagNewTask: CheckBox
    private lateinit var cbFlagClearTop: CheckBox
    private lateinit var cbFlagSingleTop: CheckBox

    private lateinit var etText: EditText
    private lateinit var tvActivity: TextView
    private var isEditing: Boolean = false

    private var text: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach() method called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() method called")

        isEditing = savedInstanceState?.getBoolean(IS_EDITING_KEY) ?: false
        text = savedInstanceState?.getString(TEXT_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView() method called")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ui, container, false)

        // get buttons
        val btnStandard: Button = view.findViewById(R.id.btnStandard)
        val btnSingleTop: Button = view.findViewById(R.id.btnSingleTop)
        val btnSingleTask: Button = view.findViewById(R.id.btnSingleTask)
        val btnSingleInstance: Button = view.findViewById(R.id.btnSingleInstance)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        // get checkboxes
        cbFlagNewTask = view.findViewById(R.id.cbFlagNewTask)
        cbFlagClearTop = view.findViewById(R.id.cbFlagClearTop)
        cbFlagSingleTop = view.findViewById(R.id.cbFlagSingleTop)

        // get editText
        etText = view.findViewById(R.id.etText)
        etText.visibility = View.GONE

        // get textView
        tvActivity = view.findViewById(R.id.tvActivity)

        // set button click listeners
        btnStandard.setOnClickListener{
            val intent = createIntentForStartActivity(PrimaryActivity::class)
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
        // button click listeners that change ui state
        // edit
        btnEdit.setOnClickListener {
            isEditing = !isEditing
            if (isEditing) {
                // Editing text, display editText and remove textView
                btnEdit.text = getString(R.string.cancel)
                etText.setText(tvActivity.text)
                etText.visibility = View.VISIBLE
                tvActivity.visibility = View.INVISIBLE
            } else {
                // Remove editText and display textView
                btnEdit.text = getString(R.string.edit)
                etText.visibility = View.GONE
                tvActivity.visibility = View.VISIBLE
            }
        }
        // save
        btnSave.setOnClickListener {
            tvActivity.text = etText.text
            // save text to local variable
            text = tvActivity.text.toString()

            // if editing remove edit text
            if (isEditing) {
                etText.visibility = View.GONE
                tvActivity.visibility = View.VISIBLE
                btnEdit.text = getString(R.string.edit)
            }

            // Show toast message
            Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // get data sent from parent activity and set it to TextView
        val activityName = requireArguments().getString("parent")
        tvActivity.text = text ?: activityName

        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated() method called.")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored() method called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() method called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() method called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() method called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() method called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState() method called")

        outState.putBoolean(IS_EDITING_KEY, isEditing)
        outState.putString(TEXT_KEY, text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView() method called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() method called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach() method called")
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