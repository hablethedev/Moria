package dev.legiti.hazel.moria.core.compat

import net.kyori.adventure.text.Component
import java.util.UUID
import kotlin.time.Duration

interface MoriaPlayer {
    val uuid: UUID
    val name: String
    val gamemode: MoriaGamemode
    val position: MoriaLocation
    val biome: String // should slightly optimize afk handling?
    val ping: Int
    val afkDuration: Duration
    val lastActionMillis: Long
    fun resetAfkDuration()
    fun sendMessage(text: Component)
    fun addTag(tag: String)
    fun removeTag(tag: String)
}