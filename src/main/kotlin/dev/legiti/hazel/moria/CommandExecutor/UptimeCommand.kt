package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Helper.formatAsDateTime
import dev.legiti.hazel.moria.Helper.formatAsTime
import dev.legiti.hazel.moria.Moria
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class UptimeCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        sender.sendMessage(Moria.mm.deserialize(buildString {
            append("<#6795e0>Uptime: <#607aba>${(System.currentTimeMillis() - Moria.serverStartTime).formatAsTime()}<br>")
            append("<#6795e0>Started: <#607aba>${Moria.serverStartTime.formatAsDateTime()}")
        }))
        return true
    }
}