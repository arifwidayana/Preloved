package com.arifwidayana.shared.utils.ext

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

private val defaultCurrency = DecimalFormat.getCurrencyInstance(Locale("id", "ID"))
private val formatCurrency = DecimalFormatSymbols(Locale("id", "ID"))

fun convertCurrency(value: Int): String {
    return defaultCurrency.format(value)
}

fun parseCurrencyValue(value: String): String {
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