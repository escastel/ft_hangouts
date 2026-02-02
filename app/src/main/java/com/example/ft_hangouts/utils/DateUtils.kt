package com.example.ft_hangouts.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val TIME_FORMAT = "HH:mm"
    private const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"

    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    fun formatDateTime(timestamp: Long): String {
        val sdf = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}