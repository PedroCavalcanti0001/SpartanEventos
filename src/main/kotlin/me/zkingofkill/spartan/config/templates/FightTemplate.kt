package me.zkingofkill.spartan.config.templates

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "events/fight.yml")
class FightTemplate : BaseTemplate("fight","fight"){

    override fun init() {
        super.init(SpartanEventos.instance, this)
    }
}