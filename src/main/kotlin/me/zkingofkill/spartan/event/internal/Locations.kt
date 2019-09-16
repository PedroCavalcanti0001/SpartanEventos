package me.zkingofkill.spartan.event.internal

import org.bukkit.Location

class Locations(var spawn: Location?, var lobby: Location?, var exit: Location?, var cabin: Location?){

        fun allSet():Boolean{
            return spawn != null && lobby != null && exit != null && cabin != null
        }
}