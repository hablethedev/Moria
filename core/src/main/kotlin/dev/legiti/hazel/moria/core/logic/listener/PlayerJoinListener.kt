package dev.legiti.hazel.moria.core.logic.listener

import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.logic.Moria

// stupid way but oh well

fun moriaOnPlayerJoinListener(player: MoriaPlayer) {
    Moria.joinTimes[player.uuid] = System.nanoTime()
}
