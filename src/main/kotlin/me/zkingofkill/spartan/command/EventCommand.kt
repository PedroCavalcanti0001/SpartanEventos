package me.zkingofkill.spartan.command

import me.zkingofkill.spartan.SpartanEventos
import me.zkingofkill.spartan.event.internal.Event
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EventCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("§a§lSpartanEventos §8-§7Comandos")
            sender.sendMessage("§a/evento entrar §8-§7 para entrar em um evento.")
            sender.sendMessage("§a/evento sair §8-§7 para sair de um evento.")
            if (sender.hasPermission("spartaneventos.admin")) {
                sender.sendMessage("§4/evento iniciar <nome> §8-§7 para iniciar um evento.")
                sender.sendMessage("§4/evento cancelar <nome> §8-§7 para iniciar um evento.")
                sender.sendMessage("§4/evento setpos <evento> <pos> §8-§7 setar as posições.")
            }
        } else {
            val ins = SpartanEventos.instance
            if (sender.hasPermission("§spartaneventos.admin")) {
                if (Event.parse(args[1]).isPresent) {
                    sender.sendMessage(ins.msgs.eventNotFound)
                    return false
                }
                val event = Event.parse(args[1]).get()
                if (args[0].equals("iniciar", true)) {
                    if (args.size >= 2) {
                        if (!Event.hasEventOccurring()) {
                            if (event.locations.allSet()) {
                                event.start()
                            } else {
                                sender.sendMessage(ins.msgs.allSetPositions)
                            }
                        } else {
                            sender.sendMessage(ins.msgs.hasEventOccurring)
                        }
                } else {
                    sender.sendMessage("§4/evento iniciar <nome> §8-§7 para iniciar um evento.")
                }
            }
            if (args[0].equals("setpos", true)) {
                if (args.size >= 3) {
                    if (Event.parse(args[1]).isPresent) {
                        if (!Event.hasEventOccurring()) {
                            if (event.locations.allSet()) {
                                event.start()
                            } else {
                                sender.sendMessage(ins.msgs.allSetPositions)
                            }
                        } else {
                            sender.sendMessage(ins.msgs.hasEventOccurring)
                        }
                } else {
                    sender.sendMessage("§4/evento iniciar <nome> §8-§7 para iniciar um evento.")
                }
            }
        }else{
            sender.sendMessage(ins.msgs.noPermission)
        }
    }
    return false
}
}
