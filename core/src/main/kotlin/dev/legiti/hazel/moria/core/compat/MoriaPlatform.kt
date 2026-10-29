package dev.legiti.hazel.moria.core.compat

import net.kyori.adventure.text.minimessage.MiniMessage
import org.incendo.cloud.CommandManager
import java.nio.file.Path

interface MoriaPlatform {
    val NAME: String
    val PRETTY_NAME: String
    val VERSION: String
    fun log(msg: String)
    fun warn(msg: String)
    fun onlinePlayers(): Collection<MoriaPlayer>
    fun commandManager(): CommandManager<MoriaSender>

    val mm: MiniMessage

    fun getTickTimesNano(): LongArray
    var targetTickRate: Float

    val configDirPath: Path
}

