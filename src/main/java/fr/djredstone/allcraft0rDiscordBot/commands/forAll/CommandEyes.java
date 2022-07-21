package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandEyes {
	public CommandEyes(SlashCommandInteractionEvent event) { event.reply(String.format("%1$s%2$s%1$s", Emoji.fromMarkdown("\uD83D\uDC41"), Emoji.fromMarkdown("\uD83D\uDC44"))).queue(); }
}
