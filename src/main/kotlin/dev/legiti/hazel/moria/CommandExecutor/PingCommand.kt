package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Moria
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class PingCommand : TabExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) {
                sender.sendMessage("You must be a player to use this command without arguments.")
                return true
            }

            Moria.vInfo("$sender ran command ping with no arguments, ${sender.ping}")
            sender.sendMessage(
                Moria.mm.deserialize(
                    "<#6795e0>Your ping is currently <#607aba>${sender.ping}ms<#6795e0>."
                )
            )
            return true
        }

        if (args.size == 1) {
            if (!sender.hasPermission("moria.ping.others")) {
                sender.sendMessage(Moria.mm.deserialize("<red>You do not have permission to perform this action."))
                return true
            }

            val target = Bukkit.getPlayer(args[0])

            if (target==null) {
                sender.sendMessage(Moria.mm.deserialize("<red>Player is offline or does not exist."))
                Moria.vInfo("$sender ran command ping on $target with no arguments, but they are offline")
                return true
            }

            Moria.vInfo("$sender ran command ping on $target with no arguments, ${target.ping}")

            sender.sendMessage(Moria.mm.deserialize("<#60 if (args.size != 1) return 7aba>${target.name}<#6795e0>'s ping is <#607aba>${target.ping}ms<#6795e0>."))
            return true
        }

        sender.sendMessage(Moria.mm.deserialize("<red>Usage: /ping [player]"))
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String?> {
        if (args.size != 1) return emptyList()

        return Bukkit.getOnlinePlayers().map{ it.name }.filter{ it.startsWith(args[0],ignoreCase=true) }
    }
}