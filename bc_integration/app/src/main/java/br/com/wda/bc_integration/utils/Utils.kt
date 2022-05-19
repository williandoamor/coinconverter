package br.com.wda.bc_integration.utils

import android.util.Log
import org.joda.time.LocalDate
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

/*Substratc dade*/
fun getSubstractDate():String {
    return try {

        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)

        val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        format.format(calendar.time)

    }catch (exception: Exception){
        Log.e(TAG, "getSubstractDate - erro ao subtrair data")
        throw exception
    }

}