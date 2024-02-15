package edu.temple.convoy

import android.app.Service
import android.content.Intent
import android.os.IBinder


/*
Service that updates the users location every 10m once convoy has been created.
Once convoy is ended or left, stop running
 */
class UpdateLocService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}

fun updateLocation() {
    // start foreground service that provides location updates


}