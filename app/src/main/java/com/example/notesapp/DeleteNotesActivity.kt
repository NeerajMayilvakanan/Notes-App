package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var demail:String= ""
class DeleteNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_notes)
        demail = intent.getStringExtra("userEmail").toString()
        setUpListOfDataIntoRecyclerView()
    }

    private fun getItemsList(): ArrayList<DataModelClass> {

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        return databaseHandler.viewData(demail)
    }
    private fun setUpListOfDataIntoRecyclerView(){
        val rvItemsList = findViewById<RecyclerView>(R.id.deletervItemsList)
        val tvNoRecordsAvailable = findViewById<TextView>(R.id.deleteNoRecordsAvailable)

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE
            rvItemsList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = DeleteItemAdapter( this,getItemsList())

            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }


    fun deleteRecordAlertDialog(dataModelClass: DataModelClass) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Note")
        //set message for alert dialog
        builder.setMessage("Are you sure you wants to delete ${dataModelClass.title}.")
        builder.setIcon(R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status = databaseHandler.deleteNotes(DataModelClass(dataModelClass.dId, "", "",""))
            if (status > -1) {
                Toast.makeText(
                    applicationContext,
                    "Note deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()

                setUpListOfDataIntoRecyclerView()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }
}