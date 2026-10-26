package dev.legiti.hazel.moria.core.logic.listener.helper

import dev.legiti.hazel.moria.core.compat.MoriaEvents
import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.logic.listener.moriaOnPlayerJoinListener
import dev.legiti.hazel.moria.core.logic.listener.moriaOnPlayerLeaveListener

class MoriaListeners(private val platform: MoriaPlatform) {
    fun register(events: MoriaEvents) {
        events.onPlayerJoin { p -> moriaOnPlayerJoinListener(p) }
        events.onPlayerLeave { p -> moriaOnPlayerLeaveListener(p) }
    }
}