package br.com.wda.bc_integration.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "Utilis"

/*Returns the date in the format passed by the user
* Example MM-dd-yyyy
* */
fun getDate(formato: String): String{
    return try {

        val currentDate = SimpleDateFormat(formato, Locale.getDefault()).format(Date())
        currentDate
    }catch (exception: Exception){
        Log.e(TAG, "Error capturing current date :  $exception")
        throw exception
    }
}

/*Returns currency for device locale*/
fun getCurrencyLocale(): Currency {
    return try {
        val currency = Currency.getInstance(Locale.getDefault())
        currency
    }catch (exception: Exception) {
        Log.e(TAG, "getCurrencyLocale - error capturing current currency locate : ${exception.message.toString()}")
        throw exception
    }
}