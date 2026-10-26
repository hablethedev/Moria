package dev.legiti.hazel.moria.core.logic.listener

import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.logic.Moria

fun moriaOnPlayerLeaveListener(player: MoriaPlayer) {
    Moria.joinTimes.remove(player.uuid)
}