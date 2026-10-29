package dev.legiti.hazel.moria.fabric.adapters

import dev.legiti.hazel.moria.core.compat.MoriaGamemode
import dev.legiti.hazel.moria.core.compat.MoriaLocation
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import net.kyori.adventure.text.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Util
import net.minecraft.world.level.GameType
import java.util.UUID
import kotlin.jvm.optionals.getOrNull
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

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
        )

    override val ping: Int
        get() = player.connection.latency()

    override val afkDuration: Duration
        get() = (Util.getMillis() - this.player.lastActionTime).toDuration(DurationUnit.MILLISECONDS)

    override fun resetAfkDuration() {
        player.resetLastActionTime()
    }

    override fun sendMessage(text: Component) {
        player.sendMessage(text)
    }

    override val biome
        get() = player.level().getBiome(player.blockPosition()).unwrapKey().getOrNull()?.identifier()?.toLanguageKey("biome") ?: "multiplayer.status.unknown"
    // We can just piggyback off of this. Should work fine.

    override val lastActionMillis: Long
        get() = player.lastActionTime

    override fun addTag(tag: String) {
        player.addTag(tag)
    }

    override fun removeTag(tag: String) {
        player.removeTag(tag)
    }
}