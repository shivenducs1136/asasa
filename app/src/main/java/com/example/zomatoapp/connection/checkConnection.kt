package com.example.zomatoapp.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object checkConnection {
    fun hasNetwork(context: Context): Boolean? {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager){
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false

    }
}