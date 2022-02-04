package br.com.wda.bc_integration.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputLayout
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


/*Pega o texto das edits*/
var TextInputLayout.text: String
    get() = editText?.text?.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }

/*Fecha o teclado do Dispositivo*/
fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/*Converte o Bigdecimal e retorna no
formato do idioma que o usuario escolheu*/
fun BigDecimal.formatCurrencyLocale(locale: Locale = Locale.getDefault()): String {
    return try {
        NumberFormat.getCurrencyInstance(locale).format(this)
    } catch (exception: Exception) {
        "0"
    }
}