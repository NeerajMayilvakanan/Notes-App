package com.example.notesapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var eemail:String= ""

class EditNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notes)
        eemail = intent.getStringExtra("userEmail").toString()
        setUpListOfDataIntoRecyclerView()
    }
    private fun getItemsList(): ArrayList<DataModelClass> {

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        return databaseHandler.viewData(eemail)
    }
    private fun setUpListOfDataIntoRecyclerView(){
        val rvItemsList = findViewById<RecyclerView>(R.id.editrvItemsList)
        val tvNoRecordsAvailable = findViewById<TextView>(R.id.editNoRecordsAvailable)

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE
            rvItemsList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = EditItemAdapter( this,getItemsList())

            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    fun updateRecordDialog(dataModelClass: DataModelClass) {
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)

        updateDialog.setContentView(R.layout.dialog_update)

        updateDialog.findViewById<EditText>(R.id.etUpdateTitle).setText(dataModelClass.title)

        updateDialog.findViewById<EditText>(R.id.etUpdateNotes).setText(dataModelClass.notes)

        updateDialog.findViewById<TextView>(R.id.tvUpdate).setOnClickListener(View.OnClickListener {

            val title = updateDialog.findViewById<EditText>(R.id.etUpdateTitle).text.toString()
            val note = updateDialog.findViewById<EditText>(R.id.etUpdateNotes).text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if (!title.isEmpty() && !note.isEmpty()) {
                val status =
                    databaseHandler.updateNotes(DataModelClass(dataModelClass.dId,dataModelClass.dEmail ,title, note))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Note Updated.", Toast.LENGTH_LONG).show()

                    setUpListOfDataIntoRecyclerView()

                    updateDialog.dismiss() // Dialog will be dismissed
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Title or Note cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        updateDialog.findViewById<TextView>(R.id.tvCancel).setOnClickListener(View.OnClickListener {
            updateDialog.dismiss()
        })
        //Start the dialog and display it on screen.
        updateDialog.show()
    }
}