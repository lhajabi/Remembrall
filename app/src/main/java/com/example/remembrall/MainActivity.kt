package com.example.remembrall
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var myDB: MyDatabaseHelper
    var id: ArrayList<String>? = null
    private lateinit var errand: ArrayList<String>
    private lateinit var description: ArrayList<String>
    private lateinit var customAdapter: CustomAdapter
    private lateinit var addButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //MAIN ACT VALUES
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val buttonLoc: Button= findViewById (R.id.button)
        addButton = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            val add = Intent(this, AddActivity::class.java)
            startActivity(add)
        }
        buttonLoc.setOnClickListener{
            val loc=Intent(this,SecondActivity::class.java)
            startActivity(loc)
        }

        this.myDB = MyDatabaseHelper(this)
        this.id = java.util.ArrayList()
        this.errand = java.util.ArrayList<String>()
        this.description = java.util.ArrayList<String>()

        storeDataInArrays()

        customAdapter = CustomAdapter(this, this, id!!, errand, description)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            recreate()
        }
    }

    private fun storeDataInArrays() {
        val cursor = myDB.readAllData()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                id?.add(cursor.getString(0))
                errand.add(cursor.getString(1))
                description.add(cursor.getString(2))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            confirmDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all Data?")
        builder.setPositiveButton(
            "Yes"
        ) { _, _ ->
            val myDB = MyDatabaseHelper(this)
            myDB.deleteAllData()
            //Refresh Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }

}