package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaSender
import org.incendo.cloud.CommandManager

interface MoriaCommand {
    fun register(manager: CommandManager<MoriaSender>)
}