package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class InsertNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_notes)
        val email = intent.getStringExtra("userEmail")
        val txtTitle : EditText = findViewById(R.id.txtTitle)
        val txtNote : EditText = findViewById(R.id.txtNote)
        val btnInsert : Button = findViewById(R.id.btnInsertData)
        val txtError : TextView = findViewById(R.id.txtInsertError)

        btnInsert.setOnClickListener{
            val title = (txtTitle.text).toString()
            val notes = (txtNote.text).toString()
            val databaseHandler:DatabaseHandler = DatabaseHandler(this)
            if(title!="" && notes!=""){
                val status = databaseHandler.addData(DatModelClass(email.toString(),title, notes))
                if(status>-1){
                    Toast.makeText(applicationContext,"Note Inserted", Toast.LENGTH_SHORT).show()
                    txtTitle.text.clear()
                    txtNote.text.clear()
                }
                else{
                    Toast.makeText(applicationContext,"Note not Inserted... Try Again",Toast.LENGTH_SHORT).show()
                }
                txtError.text = ""
            }
            else{
                txtError.text = "Enter valid data to insert. Cannot be empty"
            }
        }

    }
}