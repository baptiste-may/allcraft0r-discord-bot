package fr.djredstone.botdiscord.commands.forAll;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandEyes {
	public CommandEyes(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) { UtilsCommands.replyOrSend(String.format("%1$s%2$s%1$s", Emoji.fromMarkdown("\uD83D\uDC41"), Emoji.fromMarkdown("\uD83D\uDC44")), event1, event2); }
}
