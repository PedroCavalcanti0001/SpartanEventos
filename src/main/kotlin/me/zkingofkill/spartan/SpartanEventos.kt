package me.zkingofkill.spartan

import me.zkingofkill.spartan.command.EventCommand
import me.zkingofkill.spartan.config.Config
import me.zkingofkill.spartan.config.Messages
import me.zkingofkill.spartan.config.templates.*
import me.zkingofkill.spartan.date.Cache
import me.zkingofkill.spartan.engines.EventLoader
import me.zkingofkill.spartan.event.exceptions.DependencyNotFoundException
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SpartanEventos : JavaPlugin() {
    companion object {
        lateinit var instance: SpartanEventos
    }

    lateinit var config: Config
    lateinit var msgs: Messages
    lateinit var defaultTemplate: DefaultTemplate
    lateinit var hotPotatoTemplate: HotPotatoTemplate
    lateinit var archerTemplate: ArcherTemplate
    lateinit var gladiatorTemplate: GladiatorTemplate
    lateinit var fightTemplate: FightTemplate
    lateinit var economy: Economy
    lateinit var cache: Cache

    override fun onEnable() {
        instance = this

        getCommand("evento").executor = EventCommand()

        cache = Cache()
        this.initConfig()
        EventLoader().init()
        if (Bukkit.getServer().pluginManager.getPlugin("Vault") != null) {
            val rsp = Bukkit.getServer().servicesManager.getRegistration(Economy::class.java)
            economy = rsp.provider
        }else{
            throw DependencyNotFoundException("Vault")
        }

    }

    private fun initConfig() {
        config = Config()
        config.init()
        msgs = Messages()
        msgs.init()
        defaultTemplate = DefaultTemplate()
        defaultTemplate.init()
        hotPotatoTemplate = HotPotatoTemplate()
        hotPotatoTemplate.init()
        archerTemplate = ArcherTemplate()
        archerTemplate.init()
        gladiatorTemplate = GladiatorTemplate()
        gladiatorTemplate.init()
        fightTemplate = FightTemplate()
        fightTemplate.init()
    }

}