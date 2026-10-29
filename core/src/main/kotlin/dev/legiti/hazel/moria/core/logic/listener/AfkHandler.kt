
package dev.legiti.hazel.moria.core.logic.listener

import dev.legiti.hazel.moria.core.compat.MoriaLocation
import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.logic.Moria
import java.lang.System.nanoTime
import java.util.UUID
import kotlin.math.abs
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class AfkHandler(
    private val platform: MoriaPlatform
) {
    private val afkTime: Duration
        get() = Moria.configManager.config.afk.autoAfkSeconds.toDuration(DurationUnit.SECONDS)

    private companion object {
        const val POS_EPSILON_SQ = 1e-5
        const val ROT_EPSILON = 1e-3f
    }

    private class State(
        var afk: Boolean = false,
        var lastActivityNanos: Long?,
        var baseline: Long?,
        var pos: MoriaLocation
    )
    private val states = HashMap<UUID, State>()

    private fun newState(player: MoriaPlayer) = State(
        afk = false,
        lastActivityNanos = nanoTime(),
        baseline = null,
        pos = player.position
    )

    private fun state(player: MoriaPlayer) =
        states.getOrPut(player.uuid) { newState(player) }

    fun onJoin(player: MoriaPlayer) {
        states[player.uuid] = newState(player)
        player.removeTag("moria:afk")
    }

    fun remove(player: MoriaPlayer) {
        states.remove(player.uuid)
        player.removeTag("moria:afk")
    }

    fun check(players: Collection<MoriaPlayer>) {
        if (afkTime == (-1).seconds) return;

        states.keys.retainAll(players.mapTo(HashSet()) { it.uuid })
        val now = nanoTime()
        val threshold = afkTime.inWholeNanoseconds

        for (player in players) {
            val state = state(player)

            val action = player.lastActionMillis
            val baseline = state.baseline
            state.baseline = action

            if (state.afk) continue

            if (baseline != null && action != baseline) state.lastActivityNanos = now

            val last = state.lastActivityNanos ?: continue
            if (now - last >= threshold) alterState(player, state, afk = true)
        }
    }

    private fun alterState(player: MoriaPlayer, state: State, afk: Boolean) {
        if (state.afk == afk) return

        state.afk = afk
        if (afk) {
            state.pos = player.position
            onAfk(player)
            player.addTag("moria:afk")
        } else {
            onReturn(player)
            player.removeTag("moria:afk")
        }
    }

    fun toggle(player: MoriaPlayer) {
        if (state(player).afk) becomeActive(player) else becomeAfk(player)
    }


    private fun MoriaLocation.movedFrom(other: MoriaLocation): Boolean {
        if (world != other.world) return true
        val dx = x - other.x
        val dy = y - other.y
        val dz = z - other.z
        return dx * dx + dy * dy + dz * dz > POS_EPSILON_SQ ||
                abs(yaw - other.yaw) > ROT_EPSILON ||
                abs(pitch - other.pitch) > ROT_EPSILON
    }

    fun moriaOnPlayerMoveListener(player: MoriaPlayer) {
        val state = states[player.uuid] ?: return
        val pos = player.position
        val moved = pos.movedFrom(state.pos)
        state.pos = pos

        if (!moved) return
        state.lastActivityNanos = nanoTime()
        alterState(player, state, afk = false)
    }

    fun becomeAfk(player: MoriaPlayer) {
        alterState(player, state(player), afk = true)
        player.addTag("moria:afk")
    }

    fun becomeActive(player: MoriaPlayer) {
        val state = state(player)
        if (!state.afk) return
        state.lastActivityNanos = null
        state.baseline = null

        alterState(player, state, afk = false)
        player.removeTag("moria:afk")
    }

    private fun onAfk(player: MoriaPlayer) {
        player.sendMessage(
            platform.mm.deserialize("<#6795e0>You are now AFK.")
        )
    }

    private fun onReturn(player: MoriaPlayer) {
        player.sendMessage(
            platform.mm.deserialize("<#6795e0>You are no longer AFK.")
        )
    }
}
