package me.zkingofkill.spartan.event.internal

class GeneralProps(
    var type: EventType,
    var name: String,
    var minPlayers: Int,
    var maxPlayers: Int,
    var spectators: Boolean,
    var pvp: Boolean,
    var lastParticipantWins: Boolean,
    var timeGoingOn: Int,
    var enableFriendlyFire: Boolean,
    var giveTag: Boolean,
    var tag: String,
    var eventStatus: EventStatus,
    var resetEffects:Boolean,
    var announceDelay:Int,
    var announceAmount:Int
)