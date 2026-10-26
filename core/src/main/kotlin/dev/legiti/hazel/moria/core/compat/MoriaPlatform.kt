package dev.legiti.hazel.moria.core.compat

import net.kyori.adventure.text.minimessage.MiniMessage
import org.incendo.cloud.CommandManager

interface MoriaPlatform {
    val NAME: String
    val PRETTY_NAME: String
    val VERSION: String
    fun log(msg: String)
    fun onlinePlayers(): Collection<MoriaPlayer>
    fun commandManager(): CommandManager<MoriaSender>

    val mm: MiniMessage

    fun getTickTimesNano(): LongArray
    var targetTickRate: Float
}

