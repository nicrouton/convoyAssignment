package edu.temple.convoy

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker

// activity that shows buttons to start, join and leave a convoy
// also shows a map that shows the user's location updates

class MainConvoy : AppCompatActivity(), OnMapReadyCallback {

    lateinit var locationManager: LocationManager
    lateinit var locationListener: LocationListener

    lateinit var mapV: MapView
    lateinit var googleMap : GoogleMap

    lateinit var marker : Marker
    lateinit var lat: TextView
    lateinit var long: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_convoy)

        // map view variable
        mapV = findViewById(R.id.convoyMapView)

        mapV.onCreate(savedInstanceState)
        mapV.getMapAsync(this)

        // checks if user granted permission
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                123
            )
        } else {
            // gps stuff
        }


    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
    }

    override fun onResume() {
        super.onResume()
        mapV.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapV.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapV.onDestroy()
    }

}