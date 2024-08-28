package com.pcs.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class DateUtils {
    companion object {
         fun formatDate(inputDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date: Date? = inputFormat.parse(inputDate)
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
            return date?.let { outputFormat.format(it) } ?: "Invalid date"
        }
    }
}