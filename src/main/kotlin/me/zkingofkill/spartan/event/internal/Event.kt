package me.zkingofkill.spartan.event.internal

import me.zkingofkill.spartan.SpartanEventos
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.*

abstract class Event {

    open fun start() {
        generalProps.eventStatus = EventStatus.STARTING
        var announces: Int = generalProps.announceAmount
        var msgs = SpartanEventos.instance.msgs
        var list =  SpartanEventos.instance.msgs.starting
        msgs.addVariable(list, "%event_name%",generalProps.name)
        msgs.addVariable(list, "%players%",participants())
        msgs.addVariable(list, "%max_players%",generalProps.maxPlayers)
        msgs.addVariable(list, "%alerts%",announces)
        msgs.addVariable(list, "%total_alerts%",generalProps.announceAmount)
        object : BukkitRunnable() {
            override fun run() {
                msgs.sendAll(list)
                announces.minus(1)
                if (announces == 0) {
                    this.cancel()
                    begin()
                }
            }
        }.runTaskTimer(SpartanEventos.instance, 0,(this.generalProps.announceDelay*20)*this.generalProps.announceAmount.toLong())
    }

    open fun begin() {
        if(participants() >= generalProps.minPlayers) {
            generalProps.eventStatus = EventStatus.STARTED
        }else{
            stop(CancelReason.INSUFFICIENT_PLAYERS)
        }

    }

    open fun stop(cancelReason: CancelReason) {
        var msgs = SpartanEventos.instance.msgs
        var list =  SpartanEventos.instance.msgs.canceled
        generalProps.eventStatus = EventStatus.STOPPED
        msgs.addVariable(list, "%event_name%",generalProps.name)
        msgs.addVariable(list, "%reason%", cancelReason)
        msgs.sendAll(list)
    }

    abstract fun eliminate(player: Player)
    abstract var generalProps: GeneralProps
    abstract var locations: Locations

    abstract fun isParticipant(player: Player): Boolean
    abstract fun addParticipant(player: Player)

    abstract fun participants():Int

    companion object {
        fun parse(name: String): Optional<Event> {
            return SpartanEventos.instance.cache.eventos.stream().filter {
                it.generalProps.name == name
            }.findFirst()
        }

        fun hasEventOccurring():Boolean{
            return SpartanEventos.instance.cache.eventos.stream().filter {
                if(it.generalProps.eventStatus == EventStatus.STARTED
                    || it.generalProps.eventStatus == EventStatus.STARTING){
                    true
                }
                false
            }.findFirst().isPresent
        }
    }

}