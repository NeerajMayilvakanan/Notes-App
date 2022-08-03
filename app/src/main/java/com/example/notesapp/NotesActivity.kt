package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
var email : String = ""
class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
         email = intent.getStringExtra("userEmail").toString()
        val txtEmail : TextView = findViewById(R.id.notesUserEmail)
        txtEmail.text=email
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.main_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId

        if(id==R.id.menuInsertNote){
            val intent = Intent(this,InsertNotesActivity::class.java)
            intent.putExtra("userEmail", email)
            startActivity(intent)
        }
        else if(id==R.id.menuViewNotes){
            val intent = Intent(this,ViewNotesActivity::class.java)
            intent.putExtra("userEmail", email)
            startActivity(intent)
        }
        else if(id==R.id.menuEditNotes){
            val intent = Intent(this,EditNotesActivity::class.java)
            intent.putExtra("userEmail", email)
            startActivity(intent)

        }
        else if(id==R.id.menuDeleteNotes){
            val intent = Intent(this,DeleteNotesActivity::class.java)
            intent.putExtra("userEmail", email)
            startActivity(intent)

        }
        else if(id==R.id.logout){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}