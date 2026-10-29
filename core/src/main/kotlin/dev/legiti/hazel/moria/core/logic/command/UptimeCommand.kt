package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import dev.legiti.hazel.moria.core.logic.command.helper.formatAsDateTime
import dev.legiti.hazel.moria.core.logic.command.helper.formatAsTime
import org.incendo.cloud.CommandManager

class UptimeCommand(private val platform: MoriaPlatform) : MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("uptime").permission("moria.uptime").handler { ctx ->
                ctx.sender().audience.sendMessage(
                    platform.mm.deserialize(buildString {
                        append("<#6795e0>Uptime: <#607aba>${(System.currentTimeMillis() - Moria.serverStartTime).formatAsTime()}<br>")
                        append("<#6795e0>Started: <#607aba>${Moria.serverStartTime.formatAsDateTime()}")
                    })
                )
            }
        )
    }
}