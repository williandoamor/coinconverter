package br.com.wda.bc_integration.extensions


import android.content.Context
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import br.com.wda.bc_integration.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.createDialog(block: MaterialAlertDialogBuilder.() -> Unit = {}): AlertDialog {
    val builder = MaterialAlertDialogBuilder(this)
    builder.setPositiveButton(R.string.btn_OK,null)
    block(builder)
    return builder.create()
}

fun Context.createProgressDialog(): AlertDialog {
    return createDialog {
        val padding = 16
        val progressBar = ProgressBar(this@createProgressDialog)
        progressBar.setPadding(padding, padding, padding, padding)
        setView(progressBar)

        setPositiveButton(null, null)
        setCancelable(false)

    }
}