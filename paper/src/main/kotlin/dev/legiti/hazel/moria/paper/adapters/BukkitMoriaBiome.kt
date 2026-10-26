package dev.legiti.hazel.moria.paper.adapters

import dev.legiti.hazel.moria.core.compat.MoriaBiome
import org.bukkit.NamespacedKey
import org.bukkit.block.Biome

private val BY_ID: Map<String, MoriaBiome> =
    MoriaBiome.entries.filter { it != MoriaBiome.UNKNOWN }.associateBy { it.id }

fun Biome.asMoriaBiome(): MoriaBiome {
    val key = this.key
    if (key.namespace != NamespacedKey.MINECRAFT) return MoriaBiome.UNKNOWN
    return BY_ID[key.key] ?: MoriaBiome.UNKNOWN
}