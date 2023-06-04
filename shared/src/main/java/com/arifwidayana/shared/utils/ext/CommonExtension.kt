package com.arifwidayana.shared.utils.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import java.io.File
import java.net.URLEncoder
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun convertCurrency(value: Int): String {
    val defaultCurrency = DecimalFormat.getCurrencyInstance(Locale("id", "ID"))
    return defaultCurrency.format(value)
}

fun parseCurrencyValue(value: String): String {
    val formatCurrency = DecimalFormatSymbols(Locale("id", "ID"))
    val defaultDecimalFormat = DecimalFormat("#,###")
    val newCurrency = value.replace("[,.]".toRegex(), "").toBigDecimal()
    defaultDecimalFormat.decimalFormatSymbols = formatCurrency.apply { groupingSeparator = '.' }
    return defaultDecimalFormat.format(newCurrency)
}

fun clearCurrencyValue(value: String): String {
    return value.replace("[,.]".toRegex(), "")
}

fun uriToFile(context: Context, uri: Uri? = null): File? {
    return when (uri) {
        null -> {
            null
        }
        else -> {
            val file = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "${System.currentTimeMillis()}.jpg"
            )
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            file
        }
    }
}

fun whatsAppMessage(context: Context, number: String, message: String) {
    try {
        val uri = "https://api.whatsapp.com/send?phone=$number&text=${URLEncoder.encode(message,"utf-8")}".toUri()
        val intent = Intent().apply {
            data = uri
            setPackage("com.whatsapp")
        }
        ContextCompat.startActivity(context, intent, null)
    } catch (e: Exception) {
        Toast.makeText(context, "Whatsapp not installed", Toast.LENGTH_SHORT).show()
    }
}