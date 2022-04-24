package fr.djredstone.botdiscord.commands.economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.commands.economy.CommandP4;

public class CommandStopP4 {

    public CommandStopP4(MessageReceivedEvent event) {

        CommandP4.getGame().clear();
        CommandP4.setFirstUser(null);
        CommandP4.setSecondUser(null);
        CommandP4.setGameMessage(null);
        CommandP4.setTour(false);

        UtilsCommands.replyOrSend("La partie en cours a été arrêtée !", event, null);

    }

}
