package dev.legiti.hazel.moria.fabric.adapters

import dev.legiti.hazel.moria.core.compat.MoriaGamemode
import dev.legiti.hazel.moria.core.compat.MoriaLocation
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.GameType
import java.util.UUID

class FabricMoriaPlayer(private val player: ServerPlayer): MoriaPlayer {
    override val name: String = player.scoreboardName

    override val uuid: UUID = player.uuid

    override val gamemode: MoriaGamemode
        get() = when(player.gameMode.gameModeForPlayer) {// dude notch wtf were you cooking bro
            GameType.CREATIVE -> MoriaGamemode.CREATIVE
            GameType.SPECTATOR -> MoriaGamemode.SPECTATOR
            GameType.ADVENTURE -> MoriaGamemode.ADVENTURE
            GameType.SURVIVAL -> MoriaGamemode.SURVIVAL
        }

    override val position: MoriaLocation
        get() = MoriaLocation(
            world = player.level().dimension().identifier().toString(),
            x = player.x, y = player.y, z = player.z,
            yaw = player.yRot, pitch = player.xRot,
            biome = player.level().getBiome(player.blockPosition()).asMoriaBiome()
        )

    override val ping: Int
        get() = player.connection.latency()
}