package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;

public class CommandFakeBan {

    final String helpMessage = "Utilisation : " + Main.getPrefix() + "fakeban <@user> (raison)";

    public CommandFakeBan(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        if (event1 != null) {
            List<String> args = Arrays.asList(event1.getMessage().getContentDisplay().split(" "));
            if (args.size() < 2) {
                UtilsCommands.replyOrSend(helpMessage, event1, event2);
                return;
            }
        }

        User target;
        if (event1 != null) target = event1.getMessage().getMentionedMembers().get(0).getUser();
        else {
            assert event2 != null;
            target = Objects.requireNonNull(event2.getOption("fakeban_user")).getAsUser();
        }

        String reason = "Non spécifiée";
        if (event1 != null) {
            List<String> args = Arrays.asList(event1.getMessage().getContentDisplay().split(" "));
            if (args.size() >= 3) reason = args.get(2);
        } else reason = Objects.requireNonNull(event2.getOption("fakeban_raison")).getAsString();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(target.getAsTag() + " a été banni", null, target.getAvatarUrl());
        embed.setDescription("**Raison :** " + reason);
        embed.setColor(Color.DARK_GRAY);

        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
