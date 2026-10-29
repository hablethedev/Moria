package dev.legiti.hazel.moria.core.compat

import net.kyori.adventure.text.Component
import java.util.UUID
import kotlin.time.Duration

interface MoriaPlayer {
    val uuid: UUID
    val name: String
    val gamemode: MoriaGamemode
    val position: MoriaLocation
    val ping: Int
    val afkDuration: Duration
    fun resetAfkDuration()
    fun sendMessage(text: Component)
}