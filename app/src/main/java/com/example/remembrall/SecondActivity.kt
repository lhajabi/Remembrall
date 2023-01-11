package com.example.remembrall
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

class SecondActivity : AppCompatActivity() {
    private var latLong : String = "NoWhere"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val txtLoc:TextView = findViewById(R.id.TxtVLocation)
        getLocation()
        txtLoc.text=latLong

    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,),101)
        }
        var loc : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        loc.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
            override fun isCancellationRequested() = false })
            .addOnSuccessListener { location: Location? ->
                if (location == null)
                    latLong = "Null"
                else {
                    latLong = location.latitude.toString()+ location.longitude.toString()
                }
                Toast.makeText(
                    applicationContext,
                    latLong,
                    Toast.LENGTH_SHORT
                ).show()

            }
        loc.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
            override fun isCancellationRequested() = false })
            .addOnFailureListener(){
                latLong = "FAIL"
            }

        return
    }

}


