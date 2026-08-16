package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Moria
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MoriaCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        sender.sendMessage(
            Moria.mm.deserialize(
                buildString {
                    append("<#6795e0>This server is running ")
                    append("Moria v${Moria.VERSION_MAJ}.${Moria.VERSION_MIN}.<br>")
                    append("<#607aba>Credits: <#5782ed><click:open_url:'https://hazel.legiti.dev'>hablethedev</click>")
                }
            )
        )
        return true
    }
}