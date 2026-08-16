package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Moria
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class PermissionTabExecutor(private val permission: String, private val executor: TabExecutor) : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String?>? {
        if (!sender.hasPermission(permission) || !sender.hasPermission("moria")) return emptyList()
        return executor.onTabComplete(sender, command, label, args)
    }

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