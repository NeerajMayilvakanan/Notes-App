package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context : Context) :
        SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
            companion object{
                private const val DATABASE_VERSION= 1
                private const val DATABASE_NAME = "NotesDatabase"
                private const val TABLE_ACCOUNTS = "AccountsTable"
                private const val TABLE_DATA = "DataTable"


                private const val KEY_ID = "_id"
                private const val KEY_EMAIL = "email"
                private const val KEY_PASSWORD = "password"

                private const val KEY_DID = "_did"
                private const val KEY_DEMAIL = "demail"
                private const val KEY_TITLE = "title"
                private const val KEY_NOTES = "notes"

            }

            override fun onCreate(db: SQLiteDatabase?) {
                val CREATE_ACCOUNTS_TABLE = ("CREATE TABLE "+ TABLE_ACCOUNTS+"("+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_EMAIL+" TEXT, "+ KEY_PASSWORD+" TEXT "+")")
                db?.execSQL(CREATE_ACCOUNTS_TABLE)

                val CREATE_DATA_TABLE = ("CREATE TABLE "+ TABLE_DATA+"("+KEY_DID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_DEMAIL+" TEXT, "+ KEY_TITLE+" TEXT, "+ KEY_NOTES+" TEXT"+")")
                db?.execSQL(CREATE_DATA_TABLE)

            }

            override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                db!!.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACCOUNTS)
                onCreate(db)
            }
            //To insert data into Accounts
            fun addAccount(acc: AccountModelClass):Long{
                val db = this.writableDatabase
                val contentValues = ContentValues()
                contentValues.put(KEY_EMAIL,acc.email)
                contentValues.put(KEY_PASSWORD,acc.password)

                val success = db.insert(TABLE_ACCOUNTS,null,contentValues)

                db.close()
                return success
            }

            //To find the data in Accounts


            @SuppressLint("Range")
            fun findAccount(femail:String) : String{

                Log.i("NOTEA",femail);

                val selectQuery = "SELECT * FROM $TABLE_ACCOUNTS WHERE $KEY_EMAIL = '$femail'"

                Log.i("NOTEB",selectQuery);

                val db = this.readableDatabase

                var cursor: Cursor?=null

                try{
                    cursor = db.rawQuery(selectQuery,null)
                }catch (e:SQLiteException){
                    db.execSQL(selectQuery)
                    return "failed"
                }
//
                var password : String = "password"
                if(cursor.moveToFirst()){
                    do{
                        password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
                    }while (cursor.moveToNext())
                }
                db.close()
                Log.i("NOTEC",password);
                return password
            }

            //To insert data in Data table

            fun addData(data:DatModelClass):Long{
                val db = this.writableDatabase
                val contentValues = ContentValues()
                contentValues.put(KEY_DEMAIL,data.dEmail)
                contentValues.put(KEY_TITLE,data.title)
                contentValues.put(KEY_NOTES,data.notes)

                val success = db.insert(TABLE_DATA,null,contentValues)

                db.close()
                return success
            }

            //To view data

            @SuppressLint("Range")
            fun viewData(email:String):ArrayList<DataModelClass>{
                val dataList: ArrayList<DataModelClass> = ArrayList<DataModelClass>()

                // Query to select all the records from the table.
                val selectQuery = "SELECT  * FROM $TABLE_DATA WHERE $KEY_DEMAIL = '$email'"

                val db = this.readableDatabase

                var cursor: Cursor? = null

                try {
                    cursor = db.rawQuery(selectQuery, null)

                } catch (e: SQLiteException) {
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var did: Int
                var demail: String
                var title : String
                var note : String

                if (cursor.moveToFirst()) {
                    do {
                        did = cursor.getInt(cursor.getColumnIndex(KEY_DID))
                        demail = cursor.getString(cursor.getColumnIndex(KEY_DEMAIL))
                        title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                        note = cursor.getString(cursor.getColumnIndex(KEY_NOTES))

                        val emp = DataModelClass(dId = did, dEmail = demail, title = title, notes = note)
                        dataList.add(emp)

                    } while (cursor.moveToNext())
                }
                return dataList
            }

            //To update the notes data
            fun updateNotes(d: DataModelClass): Int {
                val db = this.writableDatabase
                val contentValues = ContentValues()
                contentValues.put(KEY_DEMAIL, d.dEmail)
                contentValues.put(KEY_TITLE, d.title)
                contentValues.put(KEY_NOTES,d.notes)

                // Updating Row
                val success = db.update(TABLE_DATA, contentValues, KEY_DID + "=" + d.dId, null)

                // Closing database connection
                db.close()
                return success
            }

            //To delete the notes data
            fun deleteNotes(d: DataModelClass): Int {
                val db = this.writableDatabase
                val contentValues = ContentValues()
                contentValues.put(KEY_ID, d.dId) // EmpModelClass id
                // Deleting Row
                val success = db.delete(TABLE_DATA, KEY_DID + "=" + d.dId, null)
                //2nd argument is String containing nullColumnHack

                // Closing database connection
                db.close()
                return success
            }

        }