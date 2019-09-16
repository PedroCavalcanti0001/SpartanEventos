package me.zkingofkill.spartan.config

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config
class Config : ConfigBuilder() {

    @ConfigValue(path ="mysql.host")
    var mysqlHost = "localhost"

    @ConfigValue(path ="mysql.port")
    var mysqlPort = 3306

    @ConfigValue(path ="mysql.password")
    var mysqlPassword = "123"

    @ConfigValue(path ="mysql.Username")
    var mysqlUsername = "root"

    @ConfigValue(path ="mysql.table")
    var mysqlTable = "TB_SpartanEvents"

    @ConfigValue(path ="mysql.database")
    var mysqlDatabase = "database"

    @ConfigValue
    var allowedCommands = arrayListOf("g","sc","tell",".", "evento sair")


    fun init(){
        super.init(SpartanEventos.instance, this)
    }
}