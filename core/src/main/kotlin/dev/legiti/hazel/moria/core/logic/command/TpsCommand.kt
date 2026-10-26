package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import org.incendo.cloud.CommandManager

class TpsCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("tps").permission("moria.tps").handler { ctx ->
                ctx.sender().audience.sendMessage(
                    platform.mm.deserialize(buildString {
                        append("<#6795e0>TPS: <#607aba>${platform.getTickTimesNano().tpsFromTimes().round(2)}</#6795e0>")
                    })
                )
            }
        )
    }
}