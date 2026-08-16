package dev.legiti.hazel.moria.Listener

import dev.legiti.hazel.moria.Moria
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        Moria.joinTimers[event.player.uniqueId] = System.nanoTime()
    }
}