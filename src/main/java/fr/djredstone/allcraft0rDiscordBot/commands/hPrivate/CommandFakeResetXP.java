package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandFakeResetXP extends ListenerAdapter {

    final String helpMessage = String.format("Utilisation : %1$sfakeresetxp <@user>", Main.getPrefix());

    public CommandFakeResetXP(@Nullable User target, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        if (target == null) {
            UtilsCommands.replyOrSend(helpMessage, event1, event2);
            return;
        }

        UtilsCommands.replyOrSend(String.format("L'xp de **%1$s** a bien été réinitialisé ! %2$s", target.getAsTag(), Emoji.fromMarkdown("✅")), event1, event2);

    }

}
