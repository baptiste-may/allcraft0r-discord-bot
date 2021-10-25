package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandNon extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "non")) {
			
			if (!event.getMember().hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage(Main.noPermMessage.toString()).queue();
                return;
            }
			
			StringBuilder message = new StringBuilder();
			for(String arg : args) {
				if(!arg.equalsIgnoreCase(args[0])) {
					message.append(arg);
					message.append(" ");
				}
			}
			if(message.toString() == null) {
				message.append("");
			}
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre demande a été refusée.");
			embed.setFooter(message.toString());
			embed.setColor(Color.RED);
			embed.setThumbnail("https://images.emojiterra.com/google/android-10/512px/274c.png");
			
			event.getChannel().sendMessage(embed.build()).queue();
			event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
			
		}
	}

}
