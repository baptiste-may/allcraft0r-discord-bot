package fr.djredstone.botdiscord.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandEyes extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("eyes")) {
			
			event.reply(":eye::lips::eye:").queue();
			
		}
	}

}
