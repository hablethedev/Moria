package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import dev.legiti.hazel.moria.core.logic.command.helper.moriaCommandBuilder
import dev.legiti.hazel.moria.core.logic.listener.AfkHandler
import org.incendo.cloud.CommandManager

class AfkCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.moriaCommandBuilder(platform,"afk").permission("moria.afk").handler { ctx ->
                val player = ctx.sender().player ?: run {
                    ctx.sender().audience.sendMessage(
                        platform.mm.deserialize(
                            "<red>You need to be a player to run this command!"
                        )
                    )
                    return@handler
                }

                Moria.afkHandler.toggle(player)
            }
        )
    }
}