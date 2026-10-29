package dev.legiti.hazel.moria.paper.adapters

import dev.legiti.hazel.moria.core.compat.MoriaGamemode
import dev.legiti.hazel.moria.core.compat.MoriaLocation
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import kotlin.time.Duration
import java.util.UUID
import kotlin.time.toKotlinDuration
import org.bukkit.craftbukkit.entity.CraftPlayer

class BukkitMoriaPlayer(private val player: org.bukkit.entity.Player) : MoriaPlayer {
    override val uuid: UUID = player.uniqueId

    override val name: String = player.name

    override val gamemode: MoriaGamemode
        get() = when(player.gameMode) {
            GameMode.ADVENTURE -> MoriaGamemode.ADVENTURE
            GameMode.CREATIVE -> MoriaGamemode.CREATIVE
            GameMode.SPECTATOR -> MoriaGamemode.SPECTATOR
            GameMode.SURVIVAL -> MoriaGamemode.SURVIVAL
        }

    override val position: MoriaLocation
        get() = player.location.let {
            MoriaLocation(
                world = it.world.key.toString(),
                x = it.x, y = it.y, z = it.z,
                yaw = it.yaw, pitch = it.pitch
            )
        }

    override val biome: String
        get() = player.location.block.biome.translationKey()

    override val ping: Int
        get() = player.ping

    override val afkDuration: Duration
        get() = player.idleDuration.toKotlinDuration()

    override fun resetAfkDuration() {
        player.resetIdleDuration()
    }

    override fun sendMessage(text: Component) {
        player.sendMessage(text)
    }

    override val lastActionMillis: Long
        get() = (player as CraftPlayer).handle.lastActionTime

    override fun addTag(tag: String) {
        player.addScoreboardTag(tag)
    }
    override fun removeTag(tag: String) {
        player.removeScoreboardTag(tag)
    }
}