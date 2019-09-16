package me.zkingofkill.spartan.config.templates

import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.ConfigValue

abstract class BaseTemplate(eventType:String,eventName:String) : ConfigBuilder() {

    @ConfigValue()
    var name = eventName

    @ConfigValue
    var minPlayers = 5

    @ConfigValue
    var type = eventType

    @ConfigValue
    var maxPlayers = 10

    @ConfigValue
    var spectators = true

    @ConfigValue
    var resetEffects = true

    @ConfigValue
    var pvp = false

    @ConfigValue
    var lastParticipantWins = true

    @ConfigValue
    var announceAmount = 10

    @ConfigValue
    var announceDelay = 5

    @ConfigValue
    var timeGoingOn = 15

    @ConfigValue
    var enableFriendlyFire = true

    @ConfigValue(path = "locations.exit")
    var locationExit = "null"

    @ConfigValue(path = "locations.lobby")
    var locationLobby = "null"

    @ConfigValue(path = "locations.spawn")
    var locationSpawn = "null"

    @ConfigValue(path = "locations.cabin")
    var locationCabin = "null"

    @ConfigValue
    var giveTag = false

    @ConfigValue(translateAlternativeColors = true)
    var tag = "&a[tag]"

    abstract fun init()

}