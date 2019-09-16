package me.zkingofkill.spartan.event.types.impl

import me.zkingofkill.spartan.event.internal.CancelReason
import me.zkingofkill.spartan.event.internal.Event
import me.zkingofkill.spartan.event.internal.GeneralProps
import me.zkingofkill.spartan.event.internal.Locations
import me.zkingofkill.spartan.event.types.internal.IFight
import me.zkingofkill.spartan.event.types.internal.IGladiator
import org.bukkit.entity.Player

class FightImpl(override var generalProps: GeneralProps, override var locations: Locations) : Event(), IFight {
    override fun participants(): Int {
        return 0
    }

    override fun start() {

    }

    override fun begin() {

    }

    override fun stop(cancelReason: CancelReason) {
        super.stop(cancelReason)
    }

    override fun isParticipant(player: Player): Boolean {
        return false
    }


    override fun eliminate(player: Player) {

    }

    override fun addParticipant(player: Player) {

    }
}