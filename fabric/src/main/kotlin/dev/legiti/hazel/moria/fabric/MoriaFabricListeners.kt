package dev.legiti.hazel.moria.fabric

import dev.legiti.hazel.moria.core.compat.MoriaEvents
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.fabric.adapters.FabricMoriaPlayer
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents

class MoriaFabricListeners : MoriaEvents {
    override fun onPlayerJoin(handler: (MoriaPlayer) -> Unit) {
        ServerPlayerEvents.JOIN.register { player ->
            handler(FabricMoriaPlayer(player))
        }
    }

    override fun onPlayerLeave(handler: (MoriaPlayer) -> Unit) {
        ServerPlayerEvents.LEAVE.register { player ->
            handler(FabricMoriaPlayer(player))
        }
    }
}