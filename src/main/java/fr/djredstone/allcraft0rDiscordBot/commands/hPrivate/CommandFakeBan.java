package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandFakeBan {

    public CommandFakeBan(SlashCommandInteractionEvent event) {

        User target = Objects.requireNonNull(event.getOption("user")).getAsUser();

        String reason = "Non spécifiée";
        if (event.getOption("text") != null)
            reason = Objects.requireNonNull(event.getOption("text")).getAsString();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(String.format("%1$s a été banni", target.getAsTag()), null, target.getAvatarUrl());
        embed.setDescription("**Raison :** " + reason);
        embed.setColor(Color.DARK_GRAY);

        event.replyEmbeds(embed.build()).queue();

    }

}
