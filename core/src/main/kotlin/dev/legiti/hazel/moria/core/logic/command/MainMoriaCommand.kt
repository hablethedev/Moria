package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import org.incendo.cloud.CommandManager

class MainMoriaCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("moria").handler { ctx ->
                ctx.sender().audience.sendMessage(
                    platform.mm.deserialize("<#6795e0>This server is running " +
                            "Moria v${Moria.VERSION_MAJ}.${Moria.VERSION_MIN} on <#607aba>${platform.PRETTY_NAME} ${platform.VERSION}<#6795e0>.")
                )
            }
        )
    }
}