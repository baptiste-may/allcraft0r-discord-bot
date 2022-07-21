package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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

    public Command8Ball(SlashCommandInteractionEvent event) {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(String.format("%1$s 8 Ball %1$s", Emoji.fromMarkdown("\uD83D\uDD2E")))
                .addField(String.format("__%1$s__", Objects.requireNonNull(event.getOption("text")).getAsString()), messages.get(new Random().nextInt(messages.size())), true)
                .setColor(new Color(170, 8, 239));

        event.replyEmbeds(embed.build()).queue();

    }

}
