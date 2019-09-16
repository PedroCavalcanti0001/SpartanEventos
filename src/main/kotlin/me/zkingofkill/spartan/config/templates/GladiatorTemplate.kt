package me.zkingofkill.spartan.config.templates

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "events/gladiator.yml")
class GladiatorTemplate : BaseTemplate("gladiator","gladiator"){

    override fun init() {
        super.init(SpartanEventos.instance, this)
    }
}