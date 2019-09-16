package me.zkingofkill.spartan.config.templates

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "events/parkour.yml")
class DefaultTemplate : BaseTemplate("default","parkour"){

    @ConfigValue(path = "locations.world")
    var signLocation = "null"

    @ConfigValue
    var joinWithItemsInInv = false

    override fun init() {
        super.init(SpartanEventos.instance, this)
    }
}