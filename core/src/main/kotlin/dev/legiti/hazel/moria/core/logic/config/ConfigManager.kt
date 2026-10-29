package dev.legiti.hazel.moria.core.logic.config

import com.akuleshov7.ktoml.Toml
import com.akuleshov7.ktoml.TomlInputConfig
import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.moveTo
import kotlin.io.path.readText
import kotlin.io.path.writeText

class ConfigManager(private val platform: MoriaPlatform) {
    private val file = platform.configDirPath.resolve("moria.toml")
    private val toml = Toml(
        inputConfig = TomlInputConfig(ignoreUnknownNames = true)
    )

    @Volatile
    var config: CoreConfig = CoreConfig()
        private set

    fun load(): Boolean {
        if (!file.exists()) {
            config = CoreConfig()
            save()
            return true
        }
        return try {
            config = toml.decodeFromString<CoreConfig>(file.readText())
            save()
            true
        } catch (e: Exception) {
            platform.warn("Could not parse config! Is it formatted correctly? If server is starting up, value will be default, otherwise value will not be set.\nParsing error: ${e.message}")
            false
        }
    }

    fun save() {
        file.parent.createDirectories()
        val tmp = file.resolveSibling("moria.toml.tmp")
        tmp.writeText(toml.encodeToString(config))
        tmp.moveTo(file, overwrite = true) // shouuuuuuuuuuld avoid corruption
    }
}