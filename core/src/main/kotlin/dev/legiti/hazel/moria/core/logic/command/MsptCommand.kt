package dev.legiti.hazel.moria.core.logic.command

import dev.legiti.hazel.moria.core.compat.MoriaPlatform
import dev.legiti.hazel.moria.core.compat.MoriaSender
import dev.legiti.hazel.moria.core.logic.command.helper.getTickPerformance
import dev.legiti.hazel.moria.core.logic.command.helper.round
import org.incendo.cloud.CommandManager

class MsptCommand(private val platform: MoriaPlatform): MoriaCommand {
    override fun register(manager: CommandManager<MoriaSender>) {
        manager.command(
            manager.commandBuilder("mspt").permission("moria.mspt").handler { ctx ->
                val tickPerf = platform.getTickTimesNano().getTickPerformance(platform).round(2)
                ctx.sender().audience.sendMessage(
                    platform.mm.deserialize(buildString {
                        append("<#6795e0>Mean MSPT: <#607aba>${tickPerf.meanMspt}<br>")
                        append("<#6795e0>Median MSPT: <#607aba>${tickPerf.medianMspt}<br>")
                        append("<#6795e0>95th Percentile: <#607aba>${tickPerf.percentile95Mspt}<br>")
                        append("<#6795e0>Max MSPT: <#607aba>${tickPerf.maxMspt}<br>")
                        append("<#6795e0>Min MSPT: <#607aba>${tickPerf.minMspt}<br>")
                        append("<#6795e0>Std. Deviation: <#607aba>${tickPerf.standardDeviationMspt}<br>")
                        append("<#6795e0>Theoretical Max TPS: <#607aba>${tickPerf.tps}<br>")
                        append("<#6795e0>TPS: <#607aba>${tickPerf.realTps}")
                    })
                )
            }
        )
    }
}