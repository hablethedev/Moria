package dev.legiti.hazel.moria.CommandExecutor

import dev.legiti.hazel.moria.Helper.getTickPerformance
import dev.legiti.hazel.moria.Helper.round
import dev.legiti.hazel.moria.Moria
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MsptCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val tickPerf = Bukkit.getTickTimes().getTickPerformance().round(2)

        sender.sendMessage(Moria.mm.deserialize(buildString {
            append("<#6795e0>Mean MSPT: <#607aba>${tickPerf.meanMspt}<br>")
            append("<#6795e0>Median MSPT: <#607aba>${tickPerf.medianMspt}<br>")
            append("<#6795e0>95th Percentile: <#607aba>${tickPerf.percentile95Mspt}<br>")
            append("<#6795e0>Max MSPT: <#607aba>${tickPerf.maxMspt}<br>")
            append("<#6795e0>Min MSPT: <#607aba>${tickPerf.minMspt}<br>")
            append("<#6795e0>Std. Deviation: <#607aba>${tickPerf.standardDeviationMspt}<br>")
            append("<#6795e0>Theoretical Max TPS: <#607aba>${tickPerf.tps}<br>")
            append("<#6795e0>TPS: <#607aba>${tickPerf.realTps}")
        }))

        return true
    }
}