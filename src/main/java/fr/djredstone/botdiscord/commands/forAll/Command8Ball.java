package fr.djredstone.botdiscord.commands.forAll;

import javax.annotation.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.commands.UtilsCommands;

public class Command8Ball {

    private static final List<String> messages = Arrays.asList(
            "Essaye plus tard",
            "Essaye encore",
            "Pas d'avis",
            "C'est ton destin",
            "Le sort en est jeté",
            "Une chance sur deux",
            "Repose ta question",
            "D'après moi, oui",
            "C'est certain",
            "Oui, absolument",
            "Tu peux compter dessus",
            "Sans aucun doute",
            "Très probable",
            "Oui",
            "C'est bien parti",
            "C'est non",
            "Peu probable",
            "Faut pas rêver",
            "N'y compte pas",
            "Impossible"
    );

    public Command8Ball(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2, String message) {

        User user;
        if (event1 != null) user = event1.getAuthor();
        else {
            assert event2 != null;
            user = event2.getUser();
        }

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(String.format("%1$s 8 Ball %1$s", Emoji.fromMarkdown("\uD83D\uDD2E")))
                .setColor(new Color(170, 8, 239))
                .addField(String.format("__%1$s__", message), messages.get(new Random().nextInt(messages.size())), true)
                .setFooter("Commandé par " + user.getAsTag(), user.getAvatarUrl());

        UtilsCommands.replyOrSend(embed, event1 ,event2);

    }

}
