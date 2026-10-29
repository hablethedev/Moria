package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.command.helper.onlinePlayerSuggestions
import dev.legiti.hazel.moria.core.logic.command.helper.pretty
import dev.legiti.hazel.moria.core.logic.command.helper.sessionDuration
import org.incendo.cloud.CommandManager
import org.incendo.cloud.component.CommandComponent
import org.incendo.cloud.parser.standard.StringParser

class WhoisCommand(private val platform: MoriaPlatform) : MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("whois")
                .permission("moria.whois")
                .required(
                    CommandComponent.builder<MoriaSender,String>("player", StringParser.stringParser())
                        .suggestionProvider(
                            platform.onlinePlayerSuggestions()
                        )
                ).handler { ctx ->
                    val name = ctx.get<String>("player")
                    val target = platform.onlinePlayers().firstOrNull { it.name == name }

                    ctx.sender().audience.sendMessage(
                        if (target == null) platform.mm.deserialize("<red>Player is offline or doesn't exist.")
                        else platform.mm.deserialize(buildString {
                            append("<#6795e0>Player <#607aba>${target.name}<br>")
                            append("<#6795e0>UUID: <#607aba>${target.uuid}<br>")
                            append("<#6795e0>Gamemode: <#607aba>${target.gamemode.toString().lowercase().replaceFirstChar { it.uppercase() }}<br>")
                            append("<#6795e0>Position: <#607aba>${target.position.pretty()}<br>")
                            append("<#6795e0>Ping: <#607aba>${target.ping}<br>")
                            append("<#6795e0>World: <#607aba>${target.position.world}<br>")
                            append("<#6795e0>Connected for: <#607aba>${target.sessionDuration()?.toMinutes() ?: "unknown "}min")
                        })
                    )
                }
        )
    }
}