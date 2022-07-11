package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandStopP4 {

    public CommandStopP4(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        CommandP4.getGame().clear();
        CommandP4.setFirstUser(null);
        CommandP4.setSecondUser(null);
        CommandP4.setGameMessage(null);
        CommandP4.setTour(false);

        UtilsCommands.replyOrSend("La partie en cours a été arrêtée !", event1, event2);

    }

}
