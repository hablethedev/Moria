package dev.legiti.hazel.moria.Listener

import dev.legiti.hazel.moria.Moria
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerLeaveListener : Listener  {
    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        Moria.joinTimers.remove(event.player.uniqueId)
    }
}