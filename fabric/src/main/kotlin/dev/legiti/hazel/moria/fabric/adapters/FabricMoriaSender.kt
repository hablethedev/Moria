package dev.legiti.hazel.moria.fabric.adapters

import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.compat.MoriaSender
import me.lucko.fabric.api.permissions.v0.Permissions
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.platform.modcommon.MinecraftServerAudiences
import net.minecraft.commands.CommandSourceStack

class FabricMoriaSender(val source: CommandSourceStack) : MoriaSender {
    override val audience: Audience by lazy {
        MinecraftServerAudiences.of(source.server).audience(source)
    }
    override val player: MoriaPlayer? = source.player?.let(::FabricMoriaPlayer)
    override fun hasPermission(permission: String): Boolean = Permissions.check(source, permission)
}