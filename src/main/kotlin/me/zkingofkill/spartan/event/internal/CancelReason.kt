package me.zkingofkill.spartan.event.internal

import me.zkingofkill.spartan.SpartanEventos

enum class CancelReason(reason:String) {
    STAFF(SpartanEventos.instance.msgs.staffCancelReason),
    INSUFFICIENT_PLAYERS(SpartanEventos.instance.msgs.insufficientPlayersReason)
}