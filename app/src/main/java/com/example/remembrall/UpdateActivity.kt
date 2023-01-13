package com.example.remembrall

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class UpdateActivity : AppCompatActivity() {
    private lateinit var errandInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var idTxt: String
    private lateinit var errandTxt: String
    private lateinit var descTxt: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        errandInput = findViewById(R.id.errand_input2)
        descriptionInput = findViewById(R.id.description_input2)
        updateButton = findViewById(R.id.update_button)
        deleteButton = findViewById(R.id.delete_button)

        //First we call this
        andSetIntentData

        //Set actionbar title after getAndSetIntentData method
        val ab = supportActionBar
        ab?.title = errandTxt
        updateButton.setOnClickListener { //And only then we call this
            val myDB = MyDatabaseHelper(this)
            errandTxt = errandInput.text.toString().trim { it <= ' ' }
            descTxt = descriptionInput.text.toString().trim { it <= ' ' }
            myDB.updateData(idTxt, errandTxt, descTxt)
        }
        deleteButton.setOnClickListener { confirmDialog() }
    }
    //Getting Data from Intent

    //Setting Intent Data
    private val andSetIntentData: Unit
        get() {
            if (intent.hasExtra("id") && intent.hasExtra("errand") &&
                intent.hasExtra("description")
            ) {
                //Getting Data from Intent
                idTxt = intent.getStringExtra("id").toString()
                errandTxt = intent.getStringExtra("errand").toString()
                descTxt = intent.getStringExtra("description").toString()

                //Setting Intent Data
                errandInput.setText(errandTxt)
                descriptionInput.setText(descTxt)
                Log.d("stev", "$errandTxt $descTxt")
            } else {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
            }
        }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete $errandTxt ?")
        builder.setMessage("Are you sure you want to delete $errandTxt ?")
        builder.setPositiveButton(
            "Yes"
        ) { _, _ ->
            val myDB = MyDatabaseHelper(this)
            myDB.deleteOneRow(idTxt)
            finish()
        }
        builder.setNegativeButton(
            "No"
        ) { _, _ -> }
        builder.create().show()
    }
}
