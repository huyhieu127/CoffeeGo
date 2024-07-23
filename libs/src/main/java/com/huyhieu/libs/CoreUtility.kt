package com.huyhieu.libs

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object CoreUtility {
    fun isInternetConnected(context: Context): Boolean {
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectionManager.activeNetwork ?: return false
        val actionNetwork =
            connectionManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actionNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actionNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actionNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}