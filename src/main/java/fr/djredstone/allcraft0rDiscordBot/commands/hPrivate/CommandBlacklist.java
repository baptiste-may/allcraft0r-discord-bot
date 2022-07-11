package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.classes.blacklist;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandBlacklist {

    final String helpMessage = String.format("Utilisation : %1$sblacklist add/remove <@user>", Main.getPrefix());

    public CommandBlacklist(@Nullable String action, @Nullable User target, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        if (action == null || target == null) {
            UtilsCommands.replyOrSend(helpMessage, event1, event2);
            return;
        }

        try {
            if (action.equalsIgnoreCase("add")) {
                if (blacklist.add(target)) {
                    UtilsCommands.replyOrSend(String.format("%1$s est maintenant blacklist !", target.getAsTag()), event1, event2);
                } else {
                    UtilsCommands.replyOrSend(String.format("%1$s est déjà blacklist !", target.getAsTag()), event1, event2);
                }
            } else if (action.equalsIgnoreCase("remove")) {
                if (blacklist.remove(target)) {
                    UtilsCommands.replyOrSend(String.format("%1$s n'est plus blacklist !", target.getAsTag()), event1, null);
                } else {
                    UtilsCommands.replyOrSend(String.format("%1$s n'est pas blacklist !", target.getAsTag()), event1, event2);
                }
            } else {
                UtilsCommands.replyOrSend(helpMessage, event1, event2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
