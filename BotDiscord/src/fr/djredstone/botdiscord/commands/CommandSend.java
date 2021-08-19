package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandSend extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "send")) {
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("  :warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre message a été correctement envoyé.");
			embed.setFooter(event.getAuthor().getName());
			embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
			embed.setColor(Color.GREEN);
			
			StringBuilder message = new StringBuilder();
			for(String arg : args) {
				if(!arg.equalsIgnoreCase(args[0])) {
					message.append(arg);
					message.append(" ");
				}
			}
			
			event.getChannel().sendMessage(embed.build()).queue();
			Main.jda.getTextChannelById("497141089480998912").sendMessage("Nouveau message de " + event.getAuthor().getName() + " : " + message.toString()).queue();
			event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
			
		}
		
	}
	
}
