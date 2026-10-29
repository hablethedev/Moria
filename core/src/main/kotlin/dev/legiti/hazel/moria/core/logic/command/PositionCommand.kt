package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.command.helper.facing
import dev.legiti.hazel.moria.core.logic.command.helper.moriaCommandBuilder
import dev.legiti.hazel.moria.core.logic.command.helper.xyzPretty
import org.incendo.cloud.CommandManager
import kotlin.math.floor

class PositionCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.moriaCommandBuilder(platform,"position").permission("moria.position").handler { ctx ->
                ctx.sender().audience.sendMessage(
                    if (ctx.sender().player == null) {
                        platform.mm.deserialize("<red>You need to be a player to run this command without arguments!")
                        return@handler
                    } else {
                        val player = ctx.sender().player!!
                        platform.mm.deserialize(buildString {
                            append("<#6795e0>Position: <#607aba>${player.position.xyzPretty()}</#6795e0><br>")
                            append("<#6795e0>Facing: <#607aba>${player.position.facing()}</#6795e0><br>")
                            append("<#6795e0>Biome: <#607aba><lang:${player.biome}></#6795e0><br>")
                            append("<#6795e0>Chunk: <#607aba>${floor(player.position.x / 16).toInt()}, ${floor(player.position.z / 16).toInt()}</#6795e0>")
                        })
                    }
                )
            }
        )
    }
}