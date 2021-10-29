package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandSend extends ListenerAdapter {

	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("send")) {
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("  :warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre message a été correctement envoyé.");
			embed.setFooter(event.getUser().getName());
			embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
			embed.setColor(Color.GREEN);
			
			String message = event.getOption("send_message").getAsString();
			
			event.replyEmbeds(embed.build()).queue();
			Objects.requireNonNull(Main.jda.getTextChannelById("497141089480998912")).sendMessage("Nouveau message de " + event.getUser().getName() + " : " + message).queue();
			event.getChannel().sendTyping().queue();
			
		}
		
	}
	
}
