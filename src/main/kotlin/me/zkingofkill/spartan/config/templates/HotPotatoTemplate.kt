package me.zkingofkill.spartan.config.templates

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "events/hotpotato.yml")
class HotPotatoTemplate : BaseTemplate("hot_potato","hot_potato") {

    @ConfigValue
    var timeToPassPotatoes = 30

    override fun init() {
        super.init(SpartanEventos.instance, this)
    }
}