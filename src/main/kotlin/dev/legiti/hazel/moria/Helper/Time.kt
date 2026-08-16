package dev.legiti.hazel.moria.Helper

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.formatAsTime(): String {
    var seconds = this / 1000

    val days = seconds / 86400
    seconds %= 86400

    val hours = seconds / 3600
    seconds %= 3600

    val minutes = seconds / 60
    seconds %= 60

    return buildString {
        if (days > 0) append("${days}d ")
        if (hours > 0) append("${hours}h")
        if (minutes > 0) append("${minutes}m")
        append("${seconds}s")
    }
}

fun Long.formatAsDateTime(): String {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss"))
}