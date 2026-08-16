package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Helper.round
import dev.legiti.hazel.moria.Moria
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class TpsCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        sender.sendMessage(Moria.mm.deserialize("<#6795e0>TPS: <#607aba>${Bukkit.getTPS()[0].round(2)}"))
        return true
    }
}