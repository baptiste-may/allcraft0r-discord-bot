package fr.djredstone.botdiscord.commands.hPrivate;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandFakeBan {

    final String helpMessage = String.format("Utilisation : %1$sfakeban <@user> (raison)", Main.getPrefix());

    public CommandFakeBan(MessageReceivedEvent event) {

        List<String> args = Arrays.asList(event.getMessage().getContentDisplay().split(" "));
        if (args.size() < 2) {
            UtilsCommands.replyOrSend(helpMessage, event, null);
            return;
        }

        User target;
        target = event.getMessage().getMentionedMembers().get(0).getUser();

        String reason = "Non spécifiée";
        if (args.size() >= 3) {
            final StringBuilder builder = new StringBuilder();
            for (int i = 2; i < args.size(); i++) {
                builder.append(args.get(i));
            }
            reason = builder.toString();
        }

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(String.format("%1$s a été banni", target.getAsTag()), null, target.getAvatarUrl());
        embed.setDescription("**Raison :** " + reason);
        embed.setColor(Color.DARK_GRAY);

        UtilsCommands.replyOrSend(embed, event, null);

    }

}
