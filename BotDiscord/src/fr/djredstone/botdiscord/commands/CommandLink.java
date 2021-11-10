package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class CommandLink {
	
	public CommandLink(@Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
			
		if(event1 != null) {
	   		
			event1.getChannel().sendMessage("Voici plusieurs lien en rapport avec allcraft0r :").setActionRow(
					Button.link("https://www.youtube.com/channel/UCY8ryk_01LytUhgfA5X3vFg", "Chaîne Youtube de allcraft0r"),
					Button.link("https://www.youtube.com/channel/UCQH2Kxrr6Y68ZcBWfJdtZ6A", "Chaîne Youtube Best Of Discord"),
					Button.link("https://twitter.com/bestOfAllcraft?s=09", "Compte Twitter Best of Discord")
					).queue();
	   		event1.getChannel().sendTyping().queue();
	   		event1.getMessage().delete().queue();
	   		
	    	}
	   	
	   	if(event2 != null) {
	   		
	   		event2.reply("Voici plusieurs lien en rapport avec allcraft0r :").addActionRow(
					Button.link("https://www.youtube.com/channel/UCY8ryk_01LytUhgfA5X3vFg", "Chaîne Youtube de allcraft0r"),
					Button.link("https://www.youtube.com/channel/UCQH2Kxrr6Y68ZcBWfJdtZ6A", "Chaîne Youtube Best Of Discord"),
					Button.link("https://twitter.com/bestOfAllcraft?s=09", "Compte Twitter Best of Discord")
					).queue();
	   		
	   	}
		
		
		
	}
	
}
