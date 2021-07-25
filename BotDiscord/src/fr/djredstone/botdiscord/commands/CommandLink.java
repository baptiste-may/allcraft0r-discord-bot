package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

public class CommandLink extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "link")) {
			
			event.getChannel().sendMessage("Voici plusieurs lien en rapport avec allcraft0r :").setActionRow(
					Button.link("https://www.youtube.com/channel/UCY8ryk_01LytUhgfA5X3vFg", "Chaîne Youtube de allcraft0r"),
					Button.link("https://www.youtube.com/channel/UCQH2Kxrr6Y68ZcBWfJdtZ6A", "Chaîne Youtube Best Of Discord"),
					Button.link("https://twitter.com/bestOfAllcraft?s=09", "Compte Twitter Best of Discord")
					).queue();;
			
					event.getChannel().sendTyping().queue();
					event.getMessage().delete().queue();
			
		}
	}

}
