package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandOui extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "oui")) {
			
			if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage(Main.noPermMessage).queue();
                return;
            }
			
			StringBuilder message = new StringBuilder();
			for(String arg : args) {
				if(!arg.equalsIgnoreCase(args[0])) {
					message.append(arg);
					message.append(" ");
				}
			}

			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre demande a été acceptée.");
			embed.setFooter(message.toString());
			embed.setColor(Color.GREEN);
			embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
			
			event.getChannel().sendMessage(embed.build()).queue();
			event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
			
		}
	}

}
