package me.zkingofkill.spartan.event.types.impl

import me.zkingofkill.spartan.event.internal.CancelReason
import me.zkingofkill.spartan.event.types.internal.IDefault
import me.zkingofkill.spartan.event.internal.Locations
import me.zkingofkill.spartan.event.internal.GeneralProps
import me.zkingofkill.spartan.event.internal.Event
import org.bukkit.entity.Player

class DefaultImpl(override var generalProps: GeneralProps, override var locations: Locations,
                  override var signLocation: String, override var participants: ArrayList<Player>
) : Event(), IDefault{

    override fun stop(cancelReason: CancelReason) {
        super.stop(cancelReason)
        participants.forEach { eliminate(it) }
    }

    override fun participants(): Int {
        return participants.size
    }

    override fun isParticipant(player: Player): Boolean {
        return participants.contains(player)
    }

    override fun eliminate(player: Player) {
        participants.remove(player)
        player.teleport(locations.exit)
    }
    override fun addParticipant(player: Player) {
        participants.add(player)
        player.teleport(locations.lobby)
    }

}