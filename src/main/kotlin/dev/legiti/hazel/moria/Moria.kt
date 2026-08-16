package dev.legiti.hazel.moria

import dev.legiti.hazel.moria.CommandExecutor.MoriaCommand
import dev.legiti.hazel.moria.CommandExecutor.PermissionExecutor
import dev.legiti.hazel.moria.CommandExecutor.PermissionTabExecutor
import dev.legiti.hazel.moria.CommandExecutor.PingCommand
import dev.legiti.hazel.moria.CommandExecutor.PositionCommand
import dev.legiti.hazel.moria.CommandExecutor.UptimeCommand
import dev.legiti.hazel.moria.CommandExecutor.WhoisCommand
import dev.legiti.hazel.moria.Enum.LoggingLevel
import dev.legiti.hazel.moria.Listener.PlayerJoinListener
import dev.legiti.hazel.moria.Listener.PlayerLeaveListener
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class Moria : JavaPlugin() {

    companion object {
        const val VERSION_MAJ = "0"
        const val VERSION_MIN = "1"
        const val VERSION_PAT = "1"
        const val VERSION_ADD = ""

        var LOGGING_LEVEL = LoggingLevel.NORMAL

        lateinit var inst: Moria
            private set

        var mm = MiniMessage.miniMessage()

        fun vInfo(msg: String) {
            if (LOGGING_LEVEL== LoggingLevel.VERBOSE) {
                inst.logger.info("[VERBOSE] $msg")
            }
        }

        fun vWarn(msg: String) {
            if (LOGGING_LEVEL== LoggingLevel.VERBOSE) {
                inst.logger.warning("[VERBOSE] $msg")
            }
        }

        var joinTimers  = mutableMapOf<UUID, Long>()
        var serverStartTime: Long = 0L
    }

    override fun onEnable() {
        inst = this
        serverStartTime = System.currentTimeMillis()

        setupConfig()

        logger.info("Moria v${VERSION_MAJ}.${VERSION_MIN}.${VERSION_PAT}${VERSION_ADD}, by hablethedev")
        if (LOGGING_LEVEL == LoggingLevel.VERBOSE) logger.info("Using Verbose logging.")

        connectListeners()
        connectCommands()
    }

    override fun onDisable() {
        logger.info("Moria has shutdown.")
    }

    fun setupConfig() {
        saveDefaultConfig()

        LOGGING_LEVEL = when (config.getString("logging")?.uppercase()) {
            "VERBOSE" -> LoggingLevel.VERBOSE
            "NORMAL" -> LoggingLevel.NORMAL
            else -> {
                logger.warning("Invalid logging level in config. Using Normal logging.")
                LoggingLevel.NORMAL
            }
        }
        vInfo("Finished setting up config.")
    }

    fun connectCommands() {
        vInfo("Connecting commands...")

        getCommand("moria")?.setExecutor(PermissionExecutor("moria", MoriaCommand()))
        getCommand("ping")?.setExecutor(PermissionTabExecutor("moria.ping", PingCommand()))
        getCommand("position")?.setExecutor(PermissionExecutor("moria.position", PositionCommand()))
        getCommand("whois")?.setExecutor(PermissionTabExecutor("moria.whois", WhoisCommand()))
        getCommand("uptime")?.setExecutor(PermissionExecutor("moria.uptime", UptimeCommand()))

        vInfo("Finished connecting commands.")
    }

    fun connectListeners() {
        vInfo("Connecting listeners...")

        server.pluginManager.registerEvents(PlayerJoinListener(), this)
        server.pluginManager.registerEvents(PlayerLeaveListener(), this)

        vInfo("Finished connecting listeners.")
    }
}


