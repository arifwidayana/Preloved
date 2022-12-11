package com.arifwidayana.shared.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
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
}