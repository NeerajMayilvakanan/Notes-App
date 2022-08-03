package com.example.notesapp

import java.util.regex.Pattern

class PasswordValidate {
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{6,}" +  // at least 4 characters
                "$"
    )
    public fun checkPassword(password: String): Boolean {
       if(!PASSWORD_PATTERN.matcher(password).matches()){
           return false
       }
        else{
            return true
       }
    }
}