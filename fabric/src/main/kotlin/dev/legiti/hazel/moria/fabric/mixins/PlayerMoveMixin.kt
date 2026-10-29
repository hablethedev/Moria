package dev.legiti.hazel.moria.fabric.mixins

import dev.legiti.hazel.moria.fabric.specialized.FabricPlayerMoveEvents
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import org.spongepowered.asm.mixin.Final
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

// WHY NOT JUST PROVIDE AN EVENT BROOO

@Mixin(ServerGamePacketListenerImpl::class)
abstract class PlayerMoveMixin {
    @Shadow
    @Final
    lateinit var player: ServerPlayer

    @Inject(
        method = ["handleMovePlayer"],
        at = [At("RETURN")]
    )
    private fun `moria$invoke`(
        packet: ServerboundMovePlayerPacket,
        ci: CallbackInfo
    ) {
        FabricPlayerMoveEvents.invoke(player)
    }
}