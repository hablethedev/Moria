package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Helper.facingDirectionYaw
import dev.legiti.hazel.moria.Helper.prettyName
import dev.legiti.hazel.moria.Moria
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import kotlin.math.roundToInt

class PositionCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage("You must be a player to use this command.")
            return true
        }

        val loc = sender.location
        val biome = loc.block.biome.prettyName()
        val rot = sender.location.facingDirectionYaw()

        Moria.vInfo("$sender at $loc in $biome ran position")

        sender.sendMessage(Moria.mm.deserialize(buildString {
            append("<#6795e0>Position: <#607aba>${loc.x.roundToInt()}, ${loc.y.roundToInt()}, ${loc.z.roundToInt()}<br>")
            append("<#6795e0>Facing: <#607aba>${rot}<br>")
            append("<#6795e0>Biome: <#607aba>${biome}<br>")
            append("<#6795e0>Chunk: <#607aba>${loc.chunk.x}, ${loc.chunk.z}")
        }))

        return true
    }
}