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
        const val BASE_URL = "https://gateway.marvel.com:443/v1/public/characters"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "12826ed96e16fb06ba5a0d7cfb710e3a"
        const val PRIVATE_KEY = "f88133fa86e3d4ab83967622c275755dc4f34553"
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