package me.zkingofkill.spartan.utils

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

    fun Location.serialize():String{
        return "${this.world.name};${this.x};${this.y};${this.z};${this.yaw};${this.pitch}"
    }

    fun String.locationDeserialize(): Location? {
        var args = this.split(";")
        if(args.size == 6) {
            var world = Bukkit.getWorld(args[0])
            var x = args[1].toDouble()
            var y = args[2].toDouble()
            var z = args[3].toDouble()
            var yaw = args[4].toFloat()
            var pitch = args[5].toFloat()
            return Location(world, x, y,z, yaw, pitch)
        }
        return null
    }