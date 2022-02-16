package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;

public class CommandFakeBan {

    final String helpMessage = "Utilisation : " + Main.prefix + "fakeban <@user> (raison)";

    public CommandFakeBan(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {

        if (event1 != null) {
            List<String> args = Arrays.asList(event1.getMessage().getContentDisplay().split(" "));
            if (args.size() < 2) {
                UtilsCommands.replyOrSend(helpMessage, event1, event2);
                return;
            }
        }

        Member member;
        if (event1 != null) member = event1.getMember();
        else {
            assert event2 != null;
            member = event2.getMember();
        }

        assert member != null;
        if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
            UtilsCommands.replyOrSend(Main.noPermMessage, event1, event2);
            return;
        }

        User target;
        if (event1 != null) target = event1.getMessage().getMentionedMembers().get(0).getUser();
        else target = Objects.requireNonNull(event2.getOption("fakeban_user")).getAsUser();

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
