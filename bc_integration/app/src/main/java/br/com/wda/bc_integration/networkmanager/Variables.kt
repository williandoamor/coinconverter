package br.com.wda.bc_integration.networkmanager

import android.util.Log
import kotlin.properties.Delegates

object Variables {
    private const val TAG : String = "Variables NetWork"
    var isNetworkConnected: Boolean by Delegates.observable(false){
        _,_, newValue ->
        Log.i(TAG, "Network connectivity, $newValue")
    }
}