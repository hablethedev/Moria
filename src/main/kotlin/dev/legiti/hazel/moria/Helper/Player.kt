package dev.legiti.hazel.moria.Helper

import dev.legiti.hazel.moria.Moria
import org.bukkit.entity.Player
import java.time.Duration

fun Player.sessionDuration(): Duration? {
    val joinTime = Moria.joinTimers[uniqueId] ?: return null
    return Duration.ofNanos(System.nanoTime() - joinTime)
}