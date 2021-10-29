package fr.djredstone.botdiscord.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

public class CommandLink extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("link")) {
			
			event.reply("Voici plusieurs lien en rapport avec allcraft0r :").addActionRow(
					Button.link("https://www.youtube.com/channel/UCY8ryk_01LytUhgfA5X3vFg", "Chaîne Youtube de allcraft0r"),
					Button.link("https://www.youtube.com/channel/UCQH2Kxrr6Y68ZcBWfJdtZ6A", "Chaîne Youtube Best Of Discord"),
					Button.link("https://twitter.com/bestOfAllcraft?s=09", "Compte Twitter Best of Discord")
					).queue();
			
		}
	}

}
