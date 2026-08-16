package dev.legiti.hazel.moria.Helper

import org.bukkit.Bukkit
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt
import kotlin.math.sqrt

data class TickPerformance(
    val meanMspt: Double,
    val medianMspt: Double,
    val percentile95Mspt: Double,
    val maxMspt: Double,
    val minMspt: Double,
    val standardDeviationMspt: Double,
    val tps: Double,
    val realTps: Double
)

fun LongArray.getTickPerformance(): TickPerformance {
    val mspts = map { it / 1000000.0 }.sorted()

    val mean = mspts.average()
    val deviance = mspts.sumOf { (it - mean) * (it - mean) } / mspts.size

    fun percentile(percent: Double): Double {
        return mspts[(percent * (mspts.size - 1)).toInt()]
    }

    return TickPerformance(
        meanMspt = mean,
        medianMspt = percentile(0.5),
        percentile95Mspt = percentile(0.95),
        maxMspt = mspts.last(),
        minMspt = mspts.first(),
        standardDeviationMspt = sqrt(deviance),
        tps = 1000.0 / mean,
        realTps = minOf(Bukkit.getServerTickManager().tickRate, (1000.0 / mean).toFloat()).toDouble()
    )
}

fun TickPerformance.round(decs: Int = 2): TickPerformance {
    return TickPerformance(
        meanMspt = meanMspt.round(decs),
        medianMspt = medianMspt.round(decs),
        percentile95Mspt = percentile95Mspt.round(decs),
        maxMspt = maxMspt.round(decs),
        minMspt = minMspt.round(decs),
        standardDeviationMspt = standardDeviationMspt.round(decs),
        tps = tps.round(decs),
        realTps = realTps.round(decs)
    )
}

fun Double.round(decs: Int = 2): Double {
    return BigDecimal.valueOf(this).setScale(decs, RoundingMode.HALF_UP).toDouble()
}

