package com.LCDP.marvelwiki

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constant {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443/v1/public/characters/"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        //modificare con le proprie chiavi
        const val API_KEY = "c9673a07f874b3a275665a6bf64b54cb"
        const val PRIVATE_KEY = "144e5c34cd52eb61e9cc8d3ec94dbcf7d7087653"
        const val limit = "100"

        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun isInternetConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }
}