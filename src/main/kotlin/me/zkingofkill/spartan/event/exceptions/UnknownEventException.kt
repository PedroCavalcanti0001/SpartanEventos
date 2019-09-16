package me.zkingofkill.spartan.event.exceptions

import java.lang.Exception

class UnknownEventException(event:String) : Exception() {
    init {
        println("the event $event It is unknown!")
    }
}