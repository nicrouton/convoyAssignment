package edu.temple.convoy

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.sql.Types.NULL

// activity that shows buttons to start, join and leave a convoy
// also shows a map that shows the user's location updates

class MainConvoy : AppCompatActivity(), OnMapReadyCallback {

    /*lateinit var locationManager: LocationManager
    lateinit var locationListener: LocationListener*/

    lateinit var mapV: MapView
    lateinit var googleMap : GoogleMap

    lateinit var marker : Marker
    lateinit var lat: TextView
    lateinit var long: TextView

    lateinit var convoyID : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_convoy)

        // map view variable
        mapV = findViewById(R.id.convoyMapView)
        convoyID = findViewById(R.id.convoyIDTextView)

        mapV.onCreate(savedInstanceState)
        mapV.getMapAsync(this)




        // get the convoy id from shared preferences
        val sharedLoginConvoyID = getSharedPreferences("response", Context.MODE_PRIVATE)

        val loginSharedResponse = sharedLoginConvoyID.getString("response", null)
        val sharedCreationConvoyID = getSharedPreferences("login_storage_key", Context.MODE_PRIVATE)
        if (loginSharedResponse != null) {
            convoyID.text = loginSharedResponse
        }

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
            // MarkerOptions for placing markers and CameraUpdateFaactory to create camreupdate objects

        }


    }

    override fun onMapReady(gMap: GoogleMap) {
        this.googleMap = gMap

        googleMap.uiSettings.isZoomControlsEnabled = true // Enable zoom controls

        val coordinates = LatLng(0.0, 0.0) // Set default coordinates
        val markerOptions = MarkerOptions().position(coordinates).title("Default Marker")
        googleMap.addMarker(markerOptions)
    }

    override fun onResume() {
        super.onResume()
        mapV.onResume()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
            /*locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener)*/
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

// function to create convoy
fun createConvoy() {
    // start foreground service that provides location updates

}

// function to destroy convoy
fun destroyConvoy() {

}