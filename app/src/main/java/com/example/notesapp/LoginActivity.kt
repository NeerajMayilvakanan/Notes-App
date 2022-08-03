package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailTxt : EditText = findViewById(R.id.edit_login_email)
        val passwordTxt : EditText = findViewById(R.id.edit_login_password)
        val errorTxt : TextView = findViewById(R.id.txt_login_error)
        val btnSignup : Button = findViewById(R.id.btn_main_login)

        btnSignup.setOnClickListener{
            val email = (emailTxt.text).toString()
            val password = (passwordTxt.text).toString()
            val emailObj = EmailValidator()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)


            if(email !="" && emailObj.checkEmail(email)){
                if(password!="" ){
                    var pass = databaseHandler.findAccount(email)
                    if(password==pass){
                        Toast.makeText(applicationContext,"Logging in",Toast.LENGTH_SHORT).show()
                        emailTxt.text.clear()
                        errorTxt.text = ""
                        passwordTxt.text.clear()
                        val intent = Intent(this,NotesActivity::class.java)
                        intent.putExtra("userEmail",email)
                        startActivity(intent)
                        finish()
                    }
                    else if(pass=="password"){
                        Toast.makeText(applicationContext,"User not Created... Signup now",Toast.LENGTH_LONG).show()
                        errorTxt.text="No account found"
                    }
                    else if(pass=="failed"){
                        errorTxt.text="Try again after some time"
                    }
                    else{
                        errorTxt.text = "Password Incorrect...Try Again"
                    }
                }
                else{
                    errorTxt.text = "Password cannot be empty"
                }
            }
            else{
                errorTxt.text = "Enter valid email address"
            }

        }

    }

}