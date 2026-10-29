[![Modrinth](https://raw.githubusercontent.com/intergrav/devins-badges/refs/heads/v3/assets/compact/available/modrinth_46h.png)](https://modrinth.com/plugin/moria)

---

Moria is a plugin that adds helpful bits and pieces to servers.

Currently, it has the following commands:
|Name|Functionality|Permission(s)|
|----|-------------|-------------|
|moria|Get info about Moria|moria.moria|
|ping|Show a player's ping|moria.ping, moria.ping.others|
|position|Get info about a player's position|moria.position|
|whois|Get info about a player|moria.whois|
|uptime|Get info about the server's uptime|moria.uptime|
|tps|Get the current TPS.|moria.tps|
|mspt|Get detailed info about the current TPS/MSPT.|moria.mspt|
|afk|Go afk. Also triggers automatically.|moria.afk|
|reloadmoria|Reload Moria config.|moria.reload|

Although Moria does not contain many features currently, it will continue to expand.

Moria is, and will always be (until archived), a work-in-progress plugin. Moria is source available.

Moria does not, and will not, plan to contain any telemetry.

Moria(MP) is made in Kotlin and starts support at 26.3. Any versions published for any Minecraft version lower are backports, and you should not expect the same level of support.

Moria has a config which will be located in config/ or plugins/ (depending on your platform).
It is a .toml file and should be self-explanatory.
If an invalid value is set and the server is starting up, it will be set to the default, or the previous value if the server was running.

---

Found a bug?

Please report the bug at https://github.com/hablethedev/moria/issues.

---

Regarding Moria in content use: if you create a video about Moria, firstly, thank you! Secondly, please credit me in a prominent location, with a link to this Modrinth page. I am not bothered about earning revenue, you may monetize how you want.

You may not repackage Moria in any form or place Moria behind a paywall.

Please see the LICENSE for further details.

---

Regarding compatibility: Moria is only tested on the latest (as of release) Paper & Fabric builds. Spigot & Bukkit support will not be provided. I will not provide support for server software such as Arclight or mods such as Cardboard.
