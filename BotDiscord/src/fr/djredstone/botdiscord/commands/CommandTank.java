package fr.djredstone.botdiscord.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandTank extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("tank")) {
			
			event.reply("https://tenor.com/view/tank-gif-10952763").queue();
			
		}
	}

}
