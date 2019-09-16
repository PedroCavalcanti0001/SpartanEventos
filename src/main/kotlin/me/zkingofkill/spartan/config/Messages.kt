package me.zkingofkill.spartan.config

import me.zkingofkill.spartan.SpartanEventos
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "messages.yml")
class Messages : ConfigBuilder() {

    @ConfigValue(translateAlternativeColors = true)
    var playerJoin = "&aO jogador %player% acabou de entrar!"

    @ConfigValue(translateAlternativeColors = true)
    var playerQuit = "&cO jogador %player% acabou de sair!"

    @ConfigValue(translateAlternativeColors = true)
    var noPermission = "&cVocê não tem permissão para fazer isso!"

    @ConfigValue(translateAlternativeColors = true)
    var eventNotFound = "&cEvento não encontrado!"

    @ConfigValue(translateAlternativeColors = true)
    var hasEventOccurring = "&cJá existe um evento ocorrendo no momento."

    @ConfigValue(translateAlternativeColors = true)
    var allSetPositions = "&cPara iniciar o evento todas as posições precisam estarem definidas."

    @ConfigValue(translateAlternativeColors = true)
    var starting = arrayListOf(
        "&a&lEvento iniciando &8-&7 %event_name%",
        "&aParticipantes: &6%players%/%max_players%",
        "Avisos: %alerts%/%total_alerts%")

    @ConfigValue(translateAlternativeColors = true)
    var canceled = arrayListOf(
        "&a&lEvento cancelado &8-&7 %event_name%",
        "&aMotivo: &c%reason%")

    @ConfigValue(translateAlternativeColors = true)
    var insufficientPlayersReason = "Falta de players"

    @ConfigValue(translateAlternativeColors = true)
    var staffCancelReason = "Staff cancelou"






    fun init() {
        super.init(SpartanEventos.instance, this)
    }
}