package dev.legiti.hazel.moria.core.logic

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.logic.command.helper.MoriaCommands
import java.util.UUID

class Moria(private val platform: MoriaPlatform) {
    companion object {
        const val VERSION_MAJ = "0"
        const val VERSION_MIN = "3"
        const val VERSION_PAT = "1"
        const val VERSION_ADD = ""

        var serverStartTime: Long = 0L
        var joinTimes = mutableMapOf<UUID, Long>()
    }

    fun enable() {
        platform.log("Moria v${VERSION_MAJ}.${VERSION_MIN}.${VERSION_PAT}${VERSION_ADD}, on ${platform.NAME}.")

        serverStartTime = System.currentTimeMillis()

        MoriaCommands(platform).register(platform.commandManager())
    }
}