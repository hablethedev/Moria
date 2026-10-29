package dev.legiti.hazel.moria.paper

import dev.legiti.hazel.moria.core.compat.MoriaEvents
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.paper.adapters.BukkitMoriaPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class MoriaPaperListeners(private val plugin: MoriaPaper): MoriaEvents {
    override fun onPlayerJoin(handler: (MoriaPlayer) -> Unit) {
        plugin.server.pluginManager.registerEvents(object : Listener {
            @EventHandler
            fun on(e: PlayerJoinEvent) {
                handler(BukkitMoriaPlayer(e.player))
            }
        }, plugin)
    }

    override fun onPlayerLeave(handler: (MoriaPlayer) -> Unit) {
        plugin.server.pluginManager.registerEvents(object : Listener {
            @EventHandler
            fun on(e: PlayerQuitEvent) {
                handler(BukkitMoriaPlayer(e.player))
            }
        },plugin)
    }

    override fun schedule(seconds: Double, runnable: () -> Unit) {
        plugin.server.scheduler.runTaskTimer(plugin,
            Runnable { runnable() },
            (seconds * (plugin.targetTickRate / 2)).toLong() /* wait 1/2 of time first */,
            (seconds * (plugin.targetTickRate)).toLong() )
    }
}