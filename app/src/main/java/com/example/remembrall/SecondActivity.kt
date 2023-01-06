package com.example.remembrall
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class SecondActivity : AppCompatActivity(){
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val txtVLocation:TextView = findViewById(R.id.textView)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //txtVLocation.text= getLocation().toString()
        getLocation()
    }

    private fun getLocation() {
        val task = fusedLocationProviderClient.lastLocation
        lateinit var latLong :String
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,),101)
            return
        }
         task.addOnSuccessListener {
             if(it != null) {
                 Toast.makeText(
                     applicationContext,
                     "${it.latitude},${it.longitude}",
                     Toast.LENGTH_SHORT
                 ).show()
                 latLong="${it.latitude},${it.longitude}"
             }
         }
        return
    }


}



