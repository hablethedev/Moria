package dev.legiti.hazel.moria.core.logic.config

import kotlinx.serialization.Serializable

@Serializable
data class CoreConfig(
    val afk: AFK = AFK()
) {
    @Serializable
    data class AFK(
        val autoAfkSeconds: Int = 30
    ) {
        init {
            require(autoAfkSeconds >= -1 && autoAfkSeconds != 0) { "AFK.autoAfkSeconds must be greater than zero, or -1."}
        }
    }
}