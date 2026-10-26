package dev.legiti.hazel.moria.paper.adapters

import dev.legiti.hazel.moria.core.compat.MoriaGamemode
import dev.legiti.hazel.moria.core.compat.MoriaLocation
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import org.bukkit.GameMode
import java.util.UUID

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
                yaw = it.yaw, pitch = it.pitch,
                biome = it.block.biome.asMoriaBiome()
            )
        }

    override val ping: Int
        get() = player.ping
}