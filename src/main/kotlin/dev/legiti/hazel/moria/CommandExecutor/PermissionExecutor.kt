package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Moria
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class PermissionExecutor(private val permission: String, private val executor: CommandExecutor) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (!sender.hasPermission(permission) || !sender.hasPermission("moria")) {
            sender.sendMessage(Moria.mm.deserialize("<red>You do not have permission to perform this action."))
            return true
        }

        return executor.onCommand(sender, command, label, args)
    }

}