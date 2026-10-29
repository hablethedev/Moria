package dev.legiti.hazel.moria.core.logic.listener

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class AfkHandler(private val afkTime: Duration = 5.seconds, private val platform: MoriaPlatform) {
    private val afkPlayers = HashSet<UUID>()

    fun check(players: Collection<MoriaPlayer>) {
        for (player in players) {
            val idle = player.afkDuration >= afkTime
            val wasIdle = player.uuid in afkPlayers
            when {
                idle && !wasIdle -> { afkPlayers.add(player.uuid); onAfk(player) }
                !idle && wasIdle -> { afkPlayers.remove(player.uuid); onReturn(player) }
            }
        }
    }

    fun remove(player: UUID) { afkPlayers.remove(player) }

    private fun onAfk(player: MoriaPlayer) {
        platform.log("${player.name} is afk, ${afkPlayers.size}")
        player.sendMessage(platform.mm.deserialize(
            "<#6795e0>You are now AFK."
        ))
    }

    private fun onReturn(player: MoriaPlayer) {
        platform.log("${player.name} is no longer afk, ${afkPlayers.size}")
        player.sendMessage(platform.mm.deserialize(
            "<#6795e0>You are no longer AFK."
        ))
    }
}