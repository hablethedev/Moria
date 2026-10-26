package dev.legiti.hazel.moria.fabric.adapters

import dev.legiti.hazel.moria.core.compat.MoriaBiome
import net.minecraft.core.Holder
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome

private val BY_ID: Map<String, MoriaBiome> =
    MoriaBiome.entries.filter { it != MoriaBiome.UNKNOWN }.associateBy { it.id }

fun ResourceKey<Biome>.asMoriaBiome(): MoriaBiome {
    val id = this.identifier()
    if (id.namespace != Identifier.DEFAULT_NAMESPACE) return MoriaBiome.UNKNOWN
    return BY_ID[id.path] ?: MoriaBiome.UNKNOWN
}

fun Holder<Biome>.asMoriaBiome(): MoriaBiome =
    this.unwrapKey().map { it.asMoriaBiome() }.orElse(MoriaBiome.UNKNOWN) ?: MoriaBiome.UNKNOWN // ???