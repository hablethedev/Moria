package dev.legiti.hazel.moria.core.logic.listener.helper

import dev.legiti.hazel.moria.core.compat.MoriaEvents
import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.logic.listener.AfkHandler
import dev.legiti.hazel.moria.core.logic.listener.moriaOnPlayerJoinListener
import dev.legiti.hazel.moria.core.logic.listener.moriaOnPlayerLeaveListener

class MoriaListeners(private val platform: MoriaPlatform) {
    fun register(events: MoriaEvents) {
        events.onPlayerJoin { p -> moriaOnPlayerJoinListener(p) }
        events.onPlayerLeave { p -> moriaOnPlayerLeaveListener(p) }

        val afkHandler = AfkHandler(platform = platform)
        events.onPlayerLeave { p -> afkHandler.remove(p.uuid) }
        events.schedule(1.0) { afkHandler.check(platform.onlinePlayers()) }
    }
}