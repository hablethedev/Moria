package dev.legiti.hazel.moria.core.compat

interface MoriaEvents {
    fun onPlayerJoin(handler: (MoriaPlayer) -> Unit)
    fun onPlayerLeave(handler: (MoriaPlayer) -> Unit)
    fun schedule(seconds: Double, runnable: () -> Unit)
}