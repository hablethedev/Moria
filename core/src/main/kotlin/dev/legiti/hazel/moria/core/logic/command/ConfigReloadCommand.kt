package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import org.incendo.cloud.CommandManager

class ConfigReloadCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("reloadmoria").permission("moria.reload").handler { ctx ->
                if (Moria.configManager.load()) {
                    ctx.sender().audience.sendMessage(
                        platform.mm.deserialize(buildString {
                            append("<#6795e0>Config reloaded successfully!")
                        })
                    )
                } else {
                    ctx.sender().audience.sendMessage(
                        platform.mm.deserialize(buildString {
                            append("<red>Config failed to reload, see console for errors.")
                        })
                    )
                }
            }
        )
    }
}