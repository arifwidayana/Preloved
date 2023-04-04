package com.arifwidayana.shared.utils.ext

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.text.DecimalFormat
import java.util.*

fun convertCurrency(value: Int): String {
    return DecimalFormat
        .getCurrencyInstance(Locale("id", "ID"))
        .format(value)
}

fun uriToFile(context: Context, uri: Uri): File {
    val file = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "${System.currentTimeMillis()}.jpg"
    )
    context.contentResolver.openInputStream(uri)?.use { inputStream ->
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }

    return file
}
