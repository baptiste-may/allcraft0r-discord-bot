package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.sql.SQLException;
import java.util.Objects;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.classes.blacklist;

public class CommandBlacklist {

    final String helpMessage = "Utilisation : blacklist add/remove <@user>";

    public CommandBlacklist(SlashCommandInteractionEvent event) {

        String action = Objects.requireNonNull(event.getOption("text")).getAsString();
        User target = Objects.requireNonNull(event.getOption("user")).getAsUser();

        try {
            if (action.equalsIgnoreCase("add")) {
                if (blacklist.add(target)) {
                    event.reply(String.format("%1$s est maintenant blacklist !", target.getAsTag())).setEphemeral(true).queue();
                } else {
                    event.reply(String.format("%1$s est déjà blacklist !", target.getAsTag())).setEphemeral(true).queue();
                }
            } else if (action.equalsIgnoreCase("remove")) {
                if (blacklist.remove(target)) {
                    event.reply(String.format("%1$s n'est plus blacklist !", target.getAsTag())).setEphemeral(true).queue();
                } else {
                    event.reply(String.format("%1$s n'est pas blacklist !", target.getAsTag())).setEphemeral(true).queue();
                }
            } else
                event.reply(helpMessage).setEphemeral(true).queue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
