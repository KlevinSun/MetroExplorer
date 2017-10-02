package com.example.sunka.metroexplorer

import android.content.Context
import android.location.Location
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.*
import java.util.*
import kotlin.concurrent.timerTask

// Location detector, simulate the professor's code, not used now...

class LocationDetector(val context: Context){
    val fusedLocationProviderClient = FusedLocationProviderClient(context)

    enum class FailureReason{
        TimeOut,
        Permission_No_granted
    }

    var locationListener: LocationListener? = null

    interface LocationListener{
        fun locationFound(location: Location)
        fun locaitonNotFound(location: Location?, reason: FailureReason)
    }

    fun locationDetect(){
        val locationRequest = LocationRequest()
        locationRequest.interval = 0L

        val selfPermission = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)

        if(selfPermission == android.content.pm.PackageManager.PERMISSION_GRANTED){
            val timer = Timer()

            val locationCallback = object : LocationCallback(){
                override fun onLocationResult(locationResult: LocationResult) {

                    fusedLocationProviderClient.removeLocationUpdates(this)
                    timer.cancel()
                    locationListener?.locationFound(locationResult.locations.first())
                }
            }

            timer.schedule(timerTask {
                fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
                val lastLocation = fusedLocationProviderClient?.lastLocation
                locationListener?.locaitonNotFound(lastLocation.result, FailureReason.TimeOut)
            },10*1000)

            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null)
        }else{
            locationListener?.locaitonNotFound(null, FailureReason.Permission_No_granted)
        }
    }
}