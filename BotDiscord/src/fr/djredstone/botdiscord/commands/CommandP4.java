package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandP4 extends ListenerAdapter {
	
	Main main;

	public CommandP4(Main main) {
		this.main = main;
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "P4")) {
			
			if(args.length == 1) {
				
				// Liste des commandes
				
			} else {
				
				if(args[1].equalsIgnoreCase("start")) {
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("**" + event.getMember().getUser().getName() + "** commence une partie de puissance 4 !");
					embed.setDescription("Clickez sur l'émoji :crossed_swords: pour l'affronter !");
					embed.setThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Puissance4_01.svg/1200px-Puissance4_01.svg.png");
					embed.setColor(Color.ORANGE);
					
					event.getChannel().sendMessage(embed.build()).queue(message -> {
						message.addReaction("⚔️").queue();
						main.P4startMessageID.add(message.getId());
						main.P4startMessageUser.put(message.getId(), event.getAuthor().getId());
						});
					event.getChannel().sendTyping().queue();
					event.getMessage().delete().queue();
					
				}
				
			}
			
		}
		
	}

}
