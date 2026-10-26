package dev.legiti.hazel.moria.paper.adapters

import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.compat.MoriaSender
import io.papermc.paper.command.brigadier.CommandSourceStack
import net.kyori.adventure.audience.Audience

class PaperMoriaSender(val source: CommandSourceStack) : MoriaSender {
    override val audience: Audience = source.sender
    override val player: MoriaPlayer? =
        (source.executor as? org.bukkit.entity.Player)?.let(::BukkitMoriaPlayer)
    override fun hasPermission(permission: String) = source.sender.hasPermission(permission)
}