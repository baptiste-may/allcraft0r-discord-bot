package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandText extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("text")) {
			
			if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage(Main.noPermMessage).queue();
                return;
            }
			
			String message = event.getOption("text").getAsString();

			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription(message.toString());
			embed.setFooter(" - " + event.getUser().getAsTag());
			embed.setColor(Color.YELLOW);
			
			if(event.getOption("text_channel").getAsMessageChannel() == null) {
				event.getChannel().sendMessage(embed.build()).queue();
				event.getChannel().sendTyping().queue();
			} else {
				event.getOption("text_channel").getAsMessageChannel().sendMessage(embed.build()).queue();
				event.getOption("text_channel").getAsMessageChannel().sendTyping().queue();
			}
			
		}
	}

}
