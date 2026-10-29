package dev.legiti.hazel.moria.core.logic

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.logic.command.helper.MoriaCommands
import dev.legiti.hazel.moria.core.logic.config.ConfigManager
import dev.legiti.hazel.moria.core.logic.listener.AfkHandler
import java.util.UUID

class Moria(private val platform: MoriaPlatform) {
    companion object {
        const val VERSION_MAJ = "0"
        const val VERSION_MIN = "4"
        const val VERSION_PAT = "0"
        const val VERSION_ADD = ""

        var serverStartTime: Long = 0L
        var joinTimes = mutableMapOf<UUID, Long>()

        lateinit var afkHandler: AfkHandler
            private set

        lateinit var configManager: ConfigManager
            private set
    }

    init {
        configManager = ConfigManager(platform = platform)
        afkHandler = AfkHandler(platform = platform)
    }

    fun enable() {
        platform.log("Moria v${VERSION_MAJ}.${VERSION_MIN}.${VERSION_PAT}${VERSION_ADD}, on ${platform.NAME}.")

        serverStartTime = System.currentTimeMillis()

        configManager.load()
        MoriaCommands(platform).register(platform.commandManager())
    }
}