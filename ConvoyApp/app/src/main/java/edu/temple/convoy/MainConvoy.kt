package edu.temple.convoy

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
    lateinit var createButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_convoy)

        // map view variable
        mapV = findViewById(R.id.convoyMapView)
        convoyID = findViewById(R.id.convoyIDTextView)
        createButton = findViewById(R.id.createConvoyButton)


        mapV.onCreate(savedInstanceState)
        mapV.getMapAsync(this)




        // get the convoy id from shared preferences

        val sharedLoginConvoyID = getSharedPreferences("response", Context.MODE_PRIVATE)
        val sharedLoginUser = getSharedPreferences("username", Context.MODE_PRIVATE)

        // get intent for whatever activity started this one
        val receivedSession = intent.getStringExtra("main_sharedpref")


        val loginSharedSession = sharedLoginConvoyID.getString(receivedSession, null)
        val loginSharedUsername = sharedLoginUser.getString("username", null)
        val sharedCreationConvoyID = getSharedPreferences("login_storage_key", Context.MODE_PRIVATE)

        // when the create convoy button is clicked run the function in a Coroutine
        createButton.setOnClickListener {
            // coroutine scope
            val scope = CoroutineScope(Job() + Dispatchers.Main)
            scope.launch {
                if (loginSharedUsername != null) {
                    if (loginSharedSession != null) {
                        createConvoy(this@MainConvoy, loginSharedSession, loginSharedUsername)
                    }
                }
            }
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
suspend fun createConvoy(context: Context, sessionKey: String, user: String) : ConvoyResponseSuccess {
    // Retrofit instance to create an instance of your API service
    val apiService = RetrofitInstance.retrofit.create(MyApiService::class.java)

    // send a post request to create a convoy
    try {
        val responseConvoy = apiService.createConvoy("CREATE", user, sessionKey)

        if (responseConvoy.isSuccessful) {
            val responseData = responseConvoy.body()

            if (responseData != null) {
                Log.d("POST request Success", "onResponse success")
                val responseJSON = responseData.toString()

                // parse out the different parts of the JSON data
                val gson = Gson()

                return gson.fromJson(responseJSON, ConvoyResponseSuccess::class.java)
            }
        }

    } catch (e: Exception) {

    }

    // start foreground service that provides location updates
    val serviceIntent = Intent(context, UpdateLocService::class.java)
    context.startService(serviceIntent)


}

// function to destroy convoy
fun destroyConvoy() {

}