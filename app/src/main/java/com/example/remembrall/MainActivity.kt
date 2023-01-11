package com.example.remembrall
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLoc: Button= findViewById (R.id.button)
        val buttonAdd: Button= findViewById (R.id.add_data)
        var flag : String = "Grocery Store"
        val spinnerVal : Spinner = findViewById(R.id.spinnerV)
        var options = arrayOf("Grocery Store","Pharmacy","Bakery","Gas Station","Roastery")
        spinnerVal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options )

        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        buttonLoc.setOnClickListener{
            val loc=Intent(this,SecondActivity::class.java)
            startActivity(loc)
        }
        buttonAdd.setOnClickListener{
            //add Spinner Value to Data base
        }
    }
}