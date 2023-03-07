package com.arifwidayana.shared.utils.ext

import java.text.DecimalFormat
import java.util.*

fun convertCurrency(value: Int): String {
    return DecimalFormat
        .getCurrencyInstance(Locale("id", "ID"))
        .format(value)
}