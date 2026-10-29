package dev.legiti.hazel.moria.core.logic.command.helper

import dev.legiti.hazel.moria.core.compat.MoriaLocation
import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import org.incendo.cloud.Command
import org.incendo.cloud.CommandManager
import org.incendo.cloud.suggestion.SuggestionProvider
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import java.time.Duration

fun MoriaPlatform.onlinePlayerSuggestions() =
    SuggestionProvider.blockingStrings<MoriaSender>{ _, _ ->
        onlinePlayers().map { it.name }
    }

fun MoriaLocation.pretty() =
    buildString {
        append(x.roundToInt()); append(", ")
        append(y.roundToInt()); append(", ")
        append(z.roundToInt()); append(" (")
        append(pitch.roundToInt()); append(",");
        append(yaw.roundToInt()); append(")")
    }

fun MoriaLocation.xyzPretty() =
    buildString {
        append(x.roundToInt()); append(", ")
        append(y.roundToInt()); append(", ")
        append(z.roundToInt());
    }

fun MoriaLocation.facing(): String {
    val dirs = arrayOf(
        "South",
        "South West",
        "West",
        "North West",
        "North",
        "North East",
        "East",
        "South East"
    )

    val i = ((yaw+22.5f) / 45f).toInt().mod(8)
    return dirs[i]
}

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

fun Double.round(decs: Int = 2): Double {
    return BigDecimal.valueOf(this).setScale(decs, BigDecimal.ROUND_HALF_UP).toDouble()
}

fun LongArray.tpsFromTimes(target: Float = 20F): Double {
    if (this.isEmpty()) return 0.0
    val avgMs = this.average() / 1_000_000.0
    return minOf(target.toDouble(), 1000.0 / avgMs.coerceAtLeast(0.001))
}

fun MoriaPlayer.sessionDuration(): Duration? {
    val joinTime = Moria.joinTimes[uuid] ?: return null
    return Duration.ofNanos(System.nanoTime() - joinTime)
}

fun CommandManager<MoriaSender>.moriaCommandBuilder(platform: MoriaPlatform, name: String): Command.Builder<MoriaSender> {
    if (platform.NAME == "fabric") {
        return commandBuilder("moria:$name",name)
    } else {
        return commandBuilder(name)
    }
}