package com.example.remembrall

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val errand: EditText = findViewById(R.id.errand_input)
        val description: EditText = findViewById(R.id.description_input)
        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            val myDB = MyDatabaseHelper(this)
            myDB.addErrand(
                errand.text.toString().trim(),
                description.text.toString().trim(),
            )
            errand.text.clear()
            description.text.clear()
        }
    }
}
