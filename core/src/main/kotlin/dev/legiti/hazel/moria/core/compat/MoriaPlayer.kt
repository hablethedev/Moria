package dev.legiti.hazel.moria.core.compat

import java.util.UUID

interface MoriaPlayer {
    val uuid: UUID
    val name: String
    val gamemode: MoriaGamemode
    val position: MoriaLocation
    val ping: Int
}