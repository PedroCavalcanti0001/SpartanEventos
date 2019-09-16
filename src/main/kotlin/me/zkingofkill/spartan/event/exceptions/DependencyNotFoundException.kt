package me.zkingofkill.spartan.event.exceptions

import java.lang.Exception

class DependencyNotFoundException(dependency:String) : Exception() {
    init {
        println("the dependence $dependency not found!")
    }
}