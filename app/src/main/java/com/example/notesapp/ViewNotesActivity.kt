package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var vemail:String= ""

class ViewNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notes)
         vemail = intent.getStringExtra("userEmail").toString()
        setUpListOfDataIntoRecyclerView()
    }

    private fun getItemsList(): ArrayList<DataModelClass> {

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        return databaseHandler.viewData(vemail)
    }
    private fun setUpListOfDataIntoRecyclerView(){
        val rvItemsList = findViewById<RecyclerView>(R.id.rvItemsList)
        val tvNoRecordsAvailable = findViewById<TextView>(R.id.noRecordsAvailable)

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE
            rvItemsList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = ItemAdapter( this,getItemsList())

            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }
}