package dev.legiti.hazel.moria.fabric

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaPlayer
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.Moria
import dev.legiti.hazel.moria.core.logic.listener.helper.MoriaListeners
import dev.legiti.hazel.moria.fabric.adapters.FabricMoriaPlayer
import dev.legiti.hazel.moria.fabric.adapters.FabricMoriaSender
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minecraft.SharedConstants
import net.minecraft.commands.CommandSourceStack
import net.minecraft.resources.Identifier
import net.minecraft.server.MinecraftServer
import org.incendo.cloud.CommandManager
import org.incendo.cloud.SenderMapper
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.fabric.FabricServerCommandManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path

class MoriaFabric : MoriaPlatform,  ModInitializer {
    override fun onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register { srv -> server = srv}
        ServerLifecycleEvents.SERVER_STOPPED.register { server = null}

        moria = Moria(this)
        moria.enable()
        listeners = MoriaListeners(this)
        listeners.register(MoriaFabricListeners())
    }

    fun id(path: String): Identifier {
        return Identifier.fromNamespaceAndPath(MOD_ID, path)
    }

    //MoriaPlatform

    companion object {
        val MOD_ID = "moria-fabric"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

        @JvmStatic
        var server: MinecraftServer? = null
    }

    private lateinit var moria: Moria
    private lateinit var listeners: MoriaListeners

    private val manager by lazy {
        FabricServerCommandManager(
            ExecutionCoordinator.simpleCoordinator(),
            SenderMapper.create<CommandSourceStack, MoriaSender>(
                { FabricMoriaSender(it)},
                {(it as FabricMoriaSender).source}
            )
        )
    }

    override val mm = MiniMessage.miniMessage()

    override fun commandManager(): CommandManager<MoriaSender> = manager

    override val NAME: String = "fabric"
    override val PRETTY_NAME: String = "Fabric"
    override val VERSION: String = SharedConstants.getCurrentVersion().name()

    override fun log(msg: String) {
        LOGGER.info("[MoriaFabric] $msg")
    }

    override fun warn(msg: String) {
        LOGGER.warn("[MoriaFabric] $msg")
    }

    override fun onlinePlayers(): Collection<MoriaPlayer> =
        server?.playerList?.players?.map { FabricMoriaPlayer(it) }.orEmpty()

    override fun getTickTimesNano(): LongArray {
        if (server == null) {
            log("Server is null in GTTN")
            return LongArray(0)
        }
        return server?.tickTimesNanos?.copyOf() ?: LongArray(0)
    }

    override var targetTickRate: Float
        get() = server?.tickRateManager()?.tickrate() ?: return 0F
        set(value) {server?.tickRateManager()?.setTickRate(value)}

    override val configDirPath: Path = FabricLoader.getInstance().configDir.toAbsolutePath().normalize()
}