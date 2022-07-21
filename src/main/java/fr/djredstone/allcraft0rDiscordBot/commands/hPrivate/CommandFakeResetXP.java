package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.util.Objects;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandFakeResetXP extends ListenerAdapter {

    public CommandFakeResetXP(SlashCommandInteractionEvent event) {

        event.reply(String.format("L'xp de **%1$s** a bien été réinitialisé ! %2$s", Objects.requireNonNull(event.getOption("user")).getAsUser().getAsTag(), Emoji.fromMarkdown("✅"))).queue();

    }

}
