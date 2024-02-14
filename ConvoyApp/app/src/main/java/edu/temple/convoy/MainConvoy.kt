package edu.temple.convoy

import android.location.LocationListener
import android.location.LocationManager
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.Marker

// activity that shows buttons to start, join and leave a convoy
// also shows a map that shows the user's location updates
class MainConvoy {
    // need a reference to location service via location manager
    lateinit var locationManager: LocationManager
    lateinit var locationListener: LocationListener

    lateinit var mapV: MapView

    lateinit var map : GoogleMap

    lateinit var marker : Marker
    lateinit var lat: TextView
    lateinit var long: TextView

}