package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Helper.prettyDisplay
import dev.legiti.hazel.moria.Helper.sessionDuration
import dev.legiti.hazel.moria.Moria
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import kotlin.text.startsWith

class WhoisCommand : TabExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.size != 1) {
            sender.sendMessage(Moria.mm.deserialize("<red>Usage: /whois [player]"))
            return true
        }

        val target = Bukkit.getPlayer(args[0])

        if (target == null) {
            sender.sendMessage(Moria.mm.deserialize("<red>Player is offline or does not exist."))
            Moria.vInfo("$sender ran command whois on $target with no arguments, but they are offline")
            return true
        }

        val uuid = target.uniqueId


        //Text 1 - #6795e0
        //Text 2 - #607aba

        Moria.vInfo("$sender ran command whois on $target.")

        sender.sendMessage(Moria.mm.deserialize(buildString {
            append("<#6795e0>Player <#607aba>${target.name}:<br>")
            append("<#6795e0>UUID: <#607aba>${uuid}<br>")
            append("<#6795e0>Gamemode: <#607aba>${target.gameMode.toString().lowercase().replaceFirstChar { it.uppercase() }}<br>")
            append("<#6795e0>Position: <#607aba>${target.location.prettyDisplay()}<br>")
            append("<#6795e0>Ping: <#607aba>${target.ping}<br>")
            append("<#6795e0>World: <#607aba>${target.world.name}<br>")
            append("<#6795e0>Connected for: <#607aba>${target.sessionDuration()?.toMinutes() ?: "unknown"}min")
        }))
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String?> {
        return Bukkit.getOnlinePlayers().map{ it.name }.filter{ it.startsWith(args[0],ignoreCase=true) }
    }
}