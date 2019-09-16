package me.zkingofkill.spartan.engines

import me.zkingofkill.spartan.SpartanEventos
import me.zkingofkill.spartan.config.templates.*
import me.zkingofkill.spartan.event.exceptions.UnknownEventException
import me.zkingofkill.spartan.event.internal.EventStatus
import me.zkingofkill.spartan.event.internal.EventType
import me.zkingofkill.spartan.event.internal.GeneralProps
import me.zkingofkill.spartan.event.internal.Locations
import me.zkingofkill.spartan.event.types.impl.*
import me.zkingofkill.spartan.utils.locationDeserialize
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class EventLoader {

    fun init() {
        for (fi in File(SpartanEventos.instance.dataFolder, "events").listFiles()) {
            val cf = YamlConfiguration.loadConfiguration(fi)
            val type = try {
                EventType.valueOf(cf.getString("type").toUpperCase())
            } catch (e: IllegalArgumentException) {
                throw UnknownEventException(cf.getString("type"))
            }
            val config = when (type) {
                EventType.DEFAULT -> DefaultTemplate().parse(DefaultTemplate::class.java, fi)
                EventType.ARCHER -> ArcherTemplate().parse(ArcherTemplate::class.java, fi)
                EventType.HOT_POTATO -> HotPotatoTemplate().parse(HotPotatoTemplate::class.java, fi)
                EventType.GLADIATOR -> GladiatorTemplate().parse(GladiatorTemplate::class.java, fi)
                EventType.FIGHT -> FightTemplate().parse(FightTemplate::class.java, fi)
            }
            val generalProps = GeneralProps(
                type = type,
                name = config.name,
                enableFriendlyFire = config.enableFriendlyFire,
                resetEffects = config.resetEffects,
                eventStatus = EventStatus.STOPPED,
                giveTag = config.giveTag,
                lastParticipantWins = config.lastParticipantWins,
                maxPlayers = config.maxPlayers,
                minPlayers = config.minPlayers,
                pvp = config.pvp,
                spectators = config.spectators,
                tag = config.tag,
                timeGoingOn = config.timeGoingOn,
                announceAmount = config.announceAmount,
                announceDelay = config.announceDelay
            )

            val locations = Locations(
                lobby = config.locationLobby.locationDeserialize(),
                cabin = config.locationCabin.locationDeserialize(),
                exit = config.locationExit.locationDeserialize(),
                spawn = config.locationSpawn.locationDeserialize()
            )

            val eventos = SpartanEventos.instance.cache.eventos

            val evento = when (config) {
                is HotPotatoTemplate -> {
                    HotPotatoImpl(
                        generalProps,
                        locations,
                        config.timeToPassPotatoes
                    )
                }
                is ArcherTemplate -> {
                    ArcherImpl(generalProps, locations)
                }
                is DefaultTemplate -> {
                    DefaultImpl(
                        generalProps,
                        locations,
                        config.signLocation,
                        arrayListOf()
                    )
                }
                is GladiatorTemplate -> {
                    GladiatorImpl(generalProps, locations)
                }
                is FightTemplate -> {
                    FightImpl(generalProps, locations)
                }
                else -> {
                    throw UnknownEventException(config.name)
                }
            }
            eventos.add(evento)
        }
    }
}