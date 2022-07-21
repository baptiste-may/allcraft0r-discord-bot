package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandUnlock {
	
	public CommandUnlock(SlashCommandInteractionEvent event) {

		TextChannel targetChannel = Objects.requireNonNull(event.getOption("channel")).getAsTextChannel();

		assert targetChannel != null;
		targetChannel.getPermissionContainer().upsertPermissionOverride(Objects.requireNonNull(event.getGuild()).getPublicRole()).setAllowed(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été unlock " + Emoji.fromMarkdown("✅"));
		embed.setAuthor(event.getUser().getAsTag(), null, event.getUser().getAvatarUrl());
		embed.setColor(Color.GREEN);

		if (event.getOption("text") != null) embed.setDescription(Objects.requireNonNull(event.getOption("text")).getAsString());

		targetChannel.sendMessageEmbeds(embed.build()).queue();
		if(targetChannel == event.getTextChannel()) event.reply("Le channel a été unlock !").setEphemeral(true).queue();
        
	}

}
