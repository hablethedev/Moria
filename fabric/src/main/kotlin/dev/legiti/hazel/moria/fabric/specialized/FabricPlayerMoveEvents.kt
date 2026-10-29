package dev.legiti.hazel.moria.fabric.specialized

import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.fabric.adapters.FabricMoriaPlayer
import net.minecraft.server.level.ServerPlayer

object FabricPlayerMoveEvents {
    private val listeners = mutableListOf<(MoriaPlayer) -> Unit>()

    fun register(handler: (MoriaPlayer) -> Unit) {
        listeners.add(handler)
    }

    fun invoke(player: ServerPlayer) {
        val mPlayer = FabricMoriaPlayer(player)
        listeners.forEach { it(mPlayer) }
    }
}