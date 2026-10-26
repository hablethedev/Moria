package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import org.incendo.cloud.CommandManager
import org.incendo.cloud.component.CommandComponent
import org.incendo.cloud.parser.standard.StringParser

class PingCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("ping").permission("moria.ping")
            .optional(
                CommandComponent.builder<MoriaSender, String>("target", StringParser.stringParser())
                    .suggestionProvider(platform.onlinePlayerSuggestions())
            )
            .handler { ctx ->
                val sender = ctx.sender()
                val name: String? = ctx.optional<String>("target").orElse(null)

                val target = if (name == null) {
                    sender.player ?: run {
                        sender.audience.sendMessage(
                            platform.mm.deserialize("<red>You need to be a player to run this command without arguments!")
                        )
                        return@handler
                    }
                } else {
                    if (!manager.hasPermission(sender, "moria.ping.others")) {
                        sender.audience.sendMessage(
                            platform.mm.deserialize("<red>You do not have permission to perform this action.")
                        )
                        return@handler
                    }
                    platform.onlinePlayers().firstOrNull {it.name.equals(name, false) } ?: run {
                        sender.audience.sendMessage(
                            platform.mm.deserialize("<red>Player is offline or doesn't exist.")
                        )
                        return@handler
                    }
                }

                val message = if (name == null) "<#6795e0>Ping: <#607aba>${target.ping}ms"
                              else "<#6795e0>${target.name}'s ping: <#607aba>${target.ping}ms"
                sender.audience.sendMessage(platform.mm.deserialize(message))
            }
        )
    }
}