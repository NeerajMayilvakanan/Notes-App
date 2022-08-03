package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val emailTxt : EditText = findViewById(R.id.edit_signup_email)
        val passwordTxt : EditText = findViewById(R.id.edit_signup_password)
        val confirmPasswordTxt : EditText = findViewById(R.id.edit_signup_re_password)
        val errorTxt : TextView = findViewById(R.id.txt_signup_error)
        val btnSignup : Button = findViewById(R.id.btn_main_signup)

        btnSignup.setOnClickListener{
            val email = (emailTxt.text).toString()
            val password = (passwordTxt.text).toString()
            val confirmPassword = (confirmPasswordTxt.text).toString()
            val emailObj = EmailValidator()
            val passwordObj = PasswordValidate()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if(email !="" && emailObj.checkEmail(email)){
                Log.i("NOTED",email.toString())
                val check = databaseHandler.findAccount(email)
                if(check=="password"){
                    if(password!="" && confirmPassword!=""){
                        if(passwordObj.checkPassword(password)){
                            if(password==confirmPassword && password!="password"){
                                val status = databaseHandler.addAccount(AccountModelClass(0,email,password))
                                if(status>-1){
                                    Toast.makeText(applicationContext,"Account Created... Go on login",Toast.LENGTH_SHORT).show()
                                    emailTxt.text.clear()
                                    passwordTxt.text.clear()
                                    confirmPasswordTxt.text.clear()

                                }else{
                                    Toast.makeText(applicationContext,"Account Not Created... Try again after some time",Toast.LENGTH_SHORT).show()

                                }
                                errorTxt.text = ""
                            }
                            else{
                                errorTxt.text = "Password mismatch or don't use password as password"
                            }
                        }
                        else{
                            errorTxt.text = "Password is too weak. Atleast 6 characters, 1 special character, no whitespaces "
                        }

                    }
                    else{
                        errorTxt.text = "Password cannot be empty"
                    }

                }
                else if(check=="failed"){
                    Toast.makeText(applicationContext,"Try Again after some time",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext,"Account already registered",Toast.LENGTH_SHORT).show()
                }

            }
            else{
                errorTxt.text = "Enter valid email address"
            }

        }


    }
}

