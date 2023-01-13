package com.example.remembrall
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener


class SecondActivity : AppCompatActivity() {
    private var latLong : String = "32.023328,35.876253"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val txtLoc:TextView = findViewById(R.id.TxtVLocation)
        val btnShow:Button = findViewById(R.id.button2)
        val btnSave:Button = findViewById(R.id.button3)
        getLocation()
        txtLoc.text=latLong
        btnShow.setOnClickListener {
            setContentView(R.layout.fragment_map)
        }
        btnSave.setOnClickListener {
            //add txtLoc.text to data base
        }

    }

    private fun getLocation(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
        }
        val loc : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        loc.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
            override fun isCancellationRequested() = false })
            .addOnSuccessListener { location: Location? ->
                latLong = if (location == null) "NULL"
                else location.latitude.toString()+ location.longitude.toString()

               Toast.makeText(this, "success $latLong", Toast.LENGTH_LONG).show()
            }
        loc.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
            override fun isCancellationRequested() = false })
            .addOnFailureListener {
                latLong = "FAIL"
                Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()
            }

        val toast = Toast.makeText(this, latLong, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
        return
    }

}


