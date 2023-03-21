package com.arifwidayana.shared.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
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

    fun getLocalDateTime(pattern: String? = null): String {
        val formatPattern = pattern ?: Constant.PATTERN_FORMAT_1
        val timeZone = TimeZone.getDefault()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern)
            val localDateTime = LocalDateTime.now(ZoneId.of(timeZone.id))
            localDateTime.format(dateTimeFormatter)
        } else {
            val simpleDateFormatter = SimpleDateFormat(formatPattern, Locale.getDefault())
            simpleDateFormatter.timeZone = timeZone
            simpleDateFormatter.format(Calendar.getInstance().time)
        }
    }

    fun convertDateTime(time: String? = null, pattern: String = Constant.PATTERN_FORMAT_1): String {
        val timeZone = TimeZone.getDefault()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
            val zonedDateTime = if (time != null) {
                ZonedDateTime.parse(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("UTC")))
            } else {
                ZonedDateTime.now(ZoneId.of(timeZone.id))
            }
            val localDateTime = zonedDateTime.toLocalDateTime()
            localDateTime.format(dateTimeFormatter)
        } else {
            val simpleDateFormatter = SimpleDateFormat(pattern, Locale.getDefault())
            val dateFormat = SimpleDateFormat(Constant.PATTERN_FORMAT_DEFAULT, Locale.getDefault())
            simpleDateFormatter.timeZone = TimeZone.getDefault()
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            return if (time != null) {
                simpleDateFormatter.format(dateFormat.parse(time) as Date)
            } else {
                simpleDateFormatter.format(Calendar.getInstance().time)
            }
        }
    }
}