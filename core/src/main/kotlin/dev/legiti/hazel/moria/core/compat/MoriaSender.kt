package dev.legiti.hazel.moria.core.compat

interface MoriaSender {
    val audience: net.kyori.adventure.audience.Audience
    val player: MoriaPlayer? //`null` if command block etc.
    fun hasPermission(permission: String): Boolean
}