package com.example.zomatoapp

import android.content.Context
import android.location.LocationManager

object LocationCheck {
    fun isLocationEnabled(context: Context):Boolean{
        var locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }
}