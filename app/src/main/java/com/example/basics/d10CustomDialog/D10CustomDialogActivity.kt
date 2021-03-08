package com.example.basics.d10CustomDialog

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AbsListView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.basics.R

class D10CustomDialogActivity : AppCompatActivity() {
    companion object {
        val TAG = "D10CustomDialogActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d10_custom_dialog)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?,
                                     menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.d10_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.d10_item_1 -> {
                Log.d(TAG, "Clicked on first item!")
                true
            }
            R.id.d10_item_2 -> {
                Log.d(TAG, "Clicked on second item!")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()

        val dlg = MyDialog(this)
        dlg.setTitle("Something different")
        dlg.show()

        dlg.setOnDismissListener {
            val floatingContextMenu = dlg.shouldShowFloatingContextMenu()
            if (floatingContextMenu) {
                registerForContextMenu(findViewById(R.id.d10_list_view))
            } else {
                enableBatchContextualActions(findViewById(R.id.d10_list_view))
            }
        }
    }

    private fun enableBatchContextualActions(listView: ListView) {
        with(listView) {
            choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {
                // Called when the action mode is created; startActionMode() was called
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.d10_context_menu, menu)
                    return true
                }

                // Called each time the action mode is shown. Always called after onCreateActionMode, but
                // may be called multiple times if the mode is invalidated.
                override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                    return false // Return false if nothing is done
                }

                // Called when the user selects a contextual menu item
                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.d10_item_1 -> {
                            Log.d(TAG, "Item 1 clicked")
                            mode.finish() // Action picked, so close the CAB
                            true
                        }
                        else -> false
                    }
                }

                override fun onItemCheckedStateChanged(
                    mode: ActionMode?,
                    position: Int,
                    id: Long,
                    checked: Boolean
                ) {
                    if (checked) {
                        getChildAt(position).setBackgroundColor(Color.CYAN)
                        Log.d(TAG, "Item checked")
                    } else {
                        getChildAt(position).setBackgroundColor(Color.WHITE)
                        Log.d(TAG, "Item unchecked")
                    }
                    return
                }

                override fun onDestroyActionMode(mode: ActionMode?) {

                }
            })
        }
    }
}