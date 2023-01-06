package com.example.remembrall
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button= findViewById (R.id.button)
        button.setOnClickListener{
            val loc=Intent(this,SecondActivity::class.java)
            startActivity(loc)
        }

    }
}