package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import javax.annotation.Nullable;
import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandFakeBan {

    final String helpMessage = String.format("Utilisation : %1$sfakeban <@user> (raison)", Main.getPrefix());

    public CommandFakeBan(@Nullable User target, @Nullable String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        if (target == null) {
            UtilsCommands.replyOrSend(helpMessage, event1, event2);
            return;
        }

        String reason = "Non spécifiée";
        if (message != null && message.equals(" "))
            reason = message;

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(String.format("%1$s a été banni", target.getAsTag()), null, target.getAvatarUrl());
        embed.setDescription("**Raison :** " + reason);
        embed.setColor(Color.DARK_GRAY);

        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
