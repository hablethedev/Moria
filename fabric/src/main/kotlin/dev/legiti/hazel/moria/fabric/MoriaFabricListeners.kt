package dev.legiti.hazel.moria.fabric

import dev.legiti.hazel.moria.core.compat.MoriaEvents
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.fabric.adapters.FabricMoriaPlayer
import dev.legiti.hazel.moria.fabric.specialized.FabricPlayerMoveEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents

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

    override fun schedule(seconds: Double, runnable: () -> Unit) {
        var elapsed = 0
        ServerTickEvents.END_SERVER_TICK.register { server ->
            val needed = seconds * (server.tickRateManager().tickrate())
            if (++elapsed >= needed)  {
                elapsed = 0
                runnable()
            }
        }
    }

    override fun onPlayerMove(handler: (MoriaPlayer) -> Unit) {
        FabricPlayerMoveEvents.register(handler)
    }
}