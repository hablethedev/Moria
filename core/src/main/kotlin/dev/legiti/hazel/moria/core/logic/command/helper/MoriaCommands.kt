package dev.legiti.hazel.moria.core.logic.command.helper

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.command.AfkCommand
import dev.legiti.hazel.moria.core.logic.command.ConfigReloadCommand
import dev.legiti.hazel.moria.core.logic.command.MainMoriaCommand
import dev.legiti.hazel.moria.core.logic.command.MoriaCommand
import dev.legiti.hazel.moria.core.logic.command.MsptCommand
import dev.legiti.hazel.moria.core.logic.command.PingCommand
import dev.legiti.hazel.moria.core.logic.command.PositionCommand
import dev.legiti.hazel.moria.core.logic.command.TpsCommand
import dev.legiti.hazel.moria.core.logic.command.UptimeCommand
import dev.legiti.hazel.moria.core.logic.command.WhoisCommand
import org.incendo.cloud.CommandManager

class MoriaCommands(private val platform: MoriaPlatform) {
    private val commands: List<MoriaCommand> = listOf(
        MainMoriaCommand(platform),
        WhoisCommand(platform),
        UptimeCommand(platform),
        MsptCommand(platform),
        TpsCommand(platform),
        PingCommand(platform),
        PositionCommand(platform),
        AfkCommand(platform),
        ConfigReloadCommand(platform),
    )

    fun register(manager: CommandManager<MoriaSender>) {
        commands.forEach{it.register(manager)}
    }
}