package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandStopP4 {

    public CommandStopP4(SlashCommandInteractionEvent event) {

        CommandP4.getGame().clear();
        CommandP4.setFirstUser(null);
        CommandP4.setSecondUser(null);
        CommandP4.setGameMessage(null);
        CommandP4.setTour(false);

        event.reply("La partie en cours a été arrêtée !").queue();

    }

}
