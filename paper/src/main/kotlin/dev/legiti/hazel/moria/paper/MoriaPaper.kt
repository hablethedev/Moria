package dev.legiti.hazel.moria.paper;

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import dev.legiti.hazel.moria.core.logic.listener.helper.MoriaListeners
import dev.legiti.hazel.moria.paper.adapters.BukkitMoriaPlayer
import dev.legiti.hazel.moria.paper.adapters.PaperMoriaSender
import io.papermc.paper.command.brigadier.CommandSourceStack
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.incendo.cloud.CommandManager
import org.incendo.cloud.SenderMapper
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.paper.PaperCommandManager

class MoriaPaper : MoriaPlatform, JavaPlugin() {
    override val NAME = "paper"
    override val PRETTY_NAME: String = "Paper"
    override val VERSION = Bukkit.getMinecraftVersion()

    private lateinit var moria: Moria
    private lateinit var listeners: MoriaListeners

    override val mm = MiniMessage.miniMessage()

    private val manager: PaperCommandManager<MoriaSender> by lazy {
        PaperCommandManager.builder(SenderMapper.create<CommandSourceStack,MoriaSender>(
            { PaperMoriaSender(it)},
            { (it as PaperMoriaSender).source}
        )).executionCoordinator(ExecutionCoordinator.simpleCoordinator()).buildOnEnable(this)
    }

    override fun commandManager(): CommandManager<MoriaSender> = manager

    override fun onEnable() {
        moria = Moria(this)
        moria.enable()
        listeners = MoriaListeners(this)
        listeners.register(MoriaPaperListeners(this))
    }

    override fun onDisable() {
        super.onDisable()
    }

    override fun log(msg: String) {
        logger.info(msg)
    }

    override fun onlinePlayers(): Collection<MoriaPlayer> =
        Bukkit.getOnlinePlayers().map{ BukkitMoriaPlayer(it) }

    override fun getTickTimesNano(): LongArray =
        Bukkit.getTickTimes()

    override var targetTickRate: Float
        get() = Bukkit.getServerTickManager().tickRate
        set(value) {Bukkit.getServerTickManager().setTickRate(value)}
}