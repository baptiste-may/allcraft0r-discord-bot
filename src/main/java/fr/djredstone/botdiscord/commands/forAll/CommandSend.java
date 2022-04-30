package fr.djredstone.botdiscord.commands.forAll;

import java.awt.Color;
import java.util.Objects;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandSend {

	public CommandSend(User user, String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription("Votre message a été correctement envoyé.");
		embed.setFooter(user.getName());
		embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
		embed.setColor(Color.GREEN);
		
		if(event2 != null) message = Objects.requireNonNull(event2.getOption("send_message")).getAsString();
		
		UtilsCommands.replyOrSend(embed, event1, event2);
		Objects.requireNonNull(Main.getJda().getTextChannelById(Main.getAdminIDChannel())).sendMessage(String.format("Nouveau message de %1$s: %2$s", user.getName(), message)).queue();
		
	}
	
}
