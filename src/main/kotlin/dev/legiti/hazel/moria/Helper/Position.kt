package dev.legiti.hazel.moria.Helper

import org.bukkit.Location
import org.bukkit.block.Biome
import kotlin.math.roundToInt

fun Biome.prettyName(): String =
    key.value().split("_").joinToString(" ") {
        word -> word.replaceFirstChar { it.uppercase() }
    }

fun Location.facingDirectionYaw(): String {
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

fun Location.prettyDisplay(): String {
    return "${x.roundToInt()}, ${y.roundToInt()}, ${z.roundToInt()}"
}