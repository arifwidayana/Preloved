package com.arifwidayana.shared.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {
    fun showDatePickerDialog(context: Context, onDatePicked: (date: String) -> Unit) {
        val date = Calendar.getInstance()
        date.apply {
            val datePickerDialog = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    onDatePicked.invoke(
                        SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.US).format(
                            this.time
                        )
                    )
                },
                get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }

    fun showTimePickerDialog(context: Context, onTimePicked: (time: String) -> Unit) {
        val time = Calendar.getInstance()
        time.apply {
            val timePickerDialog = TimePickerDialog(
                context,
                { _, hour, minute ->
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                    onTimePicked.invoke(SimpleDateFormat("HH:mm", Locale.US).format(this.time))
                },
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }
    }

    fun dateConvertToString(data: LocalDateTime): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val defaultPattern = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS")
            val convertPattern = DateTimeFormatter.ofPattern("dd-MM-uuuu'T'HH:mm:ss")
            LocalDateTime
                .parse(data.toString(), defaultPattern)
                .format(convertPattern)
            data.format(convertPattern)
        } else {
            ""
        }
    }

    fun getLocalDateTime(): Date {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
            Date.from(instant)
        } else {
            Calendar.getInstance().time
        }
    }
}