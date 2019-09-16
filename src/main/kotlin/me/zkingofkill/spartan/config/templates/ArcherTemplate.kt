package me.zkingofkill.spartan.config.templates

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.annotations.Config

@Config(path = "events/archer.yml")
class ArcherTemplate : BaseTemplate("archer", "archer"){

    override fun init() {
        super.init(SpartanEventos.instance, this)
    }
}