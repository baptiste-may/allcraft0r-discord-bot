package fr.djredstone.botdiscord.commands.hPrivate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.classes.blacklist;
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandBlacklist {

    final String helpMessage = String.format("Utilisation : %1$sblacklist add/remove <@user>", Main.getPrefix());

    public CommandBlacklist(MessageReceivedEvent event) {

        List<String> args = Arrays.asList(event.getMessage().getContentDisplay().split(" "));

        if (args.size() < 2 || event.getMessage().getMentionedMembers().isEmpty()) {
            UtilsCommands.replyOrSend(helpMessage, event, null);
            return;
        }

        User target;
        target = event.getMessage().getMentionedMembers().get(0).getUser();

        try {
            if (args.get(1).equalsIgnoreCase("add")) {
                if (blacklist.add(target)) {
                    UtilsCommands.replyOrSend(String.format("%1$s est maintenant blacklist !", target.getAsTag()), event, null);
                } else {
                    UtilsCommands.replyOrSend(String.format("%1$s est déjà blacklist !", target.getAsTag()), event, null);
                }
            } else if (args.get(1).equalsIgnoreCase("remove")) {
                if (blacklist.remove(target)) {
                    UtilsCommands.replyOrSend(String.format("%1$s n'est plus blacklist !", target.getAsTag()), event, null);
                } else {
                    UtilsCommands.replyOrSend(String.format("%1$s n'est pas blacklist !", target.getAsTag()), event, null);
                }
            } else {
                UtilsCommands.replyOrSend(helpMessage, event, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
